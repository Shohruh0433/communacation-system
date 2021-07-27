package uz.developer.communication_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.*;
import uz.developer.communication_system.entity.enums.PacketType;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.*;

import java.util.Date;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    TariffRepository tariffRepository;
    @Autowired
    SimCardRepository simCardRepository;
    @Autowired
    TariffTrafficRepository tariffTrafficRepository;
    @Autowired
    PacketRepository packetRepository;
    @Autowired
    PacketTrafficRepository packetTrafficRepository;
    @Autowired
    CodesCompanyRepository codesCompanyRepository;

    public ApiResponse getBalance(SimCard simCard) {
        return new ApiResponse("successfully", true, simCard.getBalance());
    }

    public ApiResponse changeTariff(Long tariffId, SimCard simCard) {

        Optional<Tariff> optionalTariff = tariffRepository.findById(tariffId);
        if (optionalTariff.isEmpty())
            return new ApiResponse("tariff topilmadi", false);

        Tariff newTariff = optionalTariff.get();
        if (!newTariff.isActive())
            return new ApiResponse("bu tariff aktiv emas", false);

        if (newTariff.isLegal() && !simCard.getUser().isLegal())
            return new ApiResponse("bu tariff yuridik shaxslar uchun", false);

        if (!newTariff.isLegal() && simCard.getUser().isLegal())
            return new ApiResponse("bu tariff jismoniy shaxslar uchun", false);

        TariffTraffic tariffTraffic = new TariffTraffic();
        if (!(simCard.getBalance() > newTariff.getTransitionPrice() + newTariff.getPriceOfMonth())) {

            if (!(simCard.getBalance() > newTariff.getTransitionPrice() + newTariff.getPriceOfDay()))
                return new ApiResponse("tariff ga otish uchun hisobingizda mablag yetarli emas", false);

            tariffTraffic.setNumber(simCard.getNumber());
            tariffTraffic.setCompanyCode(simCard.getCompanyCode());
            tariffTraffic.setMinuteInNet(newTariff.getMinuteInNet() / newTariff.getExpireDay());
            tariffTraffic.setMinuteOutNet(newTariff.getMinuteOutNet() / newTariff.getExpireDay());
            tariffTraffic.setSms(newTariff.getSms() / newTariff.getExpireDay());
            tariffTraffic.setNetLimitAll(newTariff.getNetLimitAll() / newTariff.getExpireDay());
            tariffTraffic.setNetLimitYoutube(newTariff.getNetLimitForYoutube() / newTariff.getExpireDay());
            tariffTraffic.setNetLimitTelegram(newTariff.getNetLimitForTelegram() / newTariff.getExpireDay());
            tariffTraffic.setTrafficExpireDate(
                    new Date(System.currentTimeMillis() + newTariff.getExpireDayMillis() / newTariff.getExpireDay()));
            simCard.setBalance(simCard.getBalance() - newTariff.getPriceOfDay());
        } else {
            tariffTraffic.setNumber(simCard.getNumber());
            tariffTraffic.setCompanyCode(simCard.getCompanyCode());
            tariffTraffic.setMinuteInNet(newTariff.getMinuteInNet());
            tariffTraffic.setMinuteOutNet(newTariff.getMinuteOutNet());
            tariffTraffic.setSms(newTariff.getSms());
            tariffTraffic.setNetLimitAll(newTariff.getNetLimitAll());
            tariffTraffic.setNetLimitYoutube(newTariff.getNetLimitForYoutube());
            tariffTraffic.setNetLimitTelegram(newTariff.getNetLimitForTelegram());
            tariffTraffic.setTrafficExpireDate(
                    new Date(System.currentTimeMillis() + newTariff.getExpireDayMillis()));
            simCard.setBalance(simCard.getBalance() - newTariff.getPriceOfMonth());
        }

        tariffTrafficRepository.save(tariffTraffic);
        simCard.setTariff(newTariff);
        simCardRepository.save(simCard);
        return new ApiResponse("tariff ga otish muvaffaqqiyatli yakunlandi", true);


    }


    public ApiResponse getMyTariffTraffic(SimCard simCard) {

        Optional<TariffTraffic> optionalTariffTraffic = tariffTrafficRepository.findByCompanyCodeAndNumber(
                simCard.getCompanyCode(), simCard.getNumber());

        return new ApiResponse("success", true, optionalTariffTraffic.get());

    }

    public ApiResponse buyPacket(SimCard simCard, Integer packedId) {

        Optional<Packet> optionalPacket = packetRepository.findById(packedId);
        if (optionalPacket.isEmpty())
            return new ApiResponse("not found ", false);

        Packet packet = optionalPacket.get();
        if (!(packet.getPrice() < simCard.getBalance()))
            return new ApiResponse("hisobingizda mablag yetarli emas ", false);

        Optional<PacketTraffic> optionalPaketTraffic = packetTrafficRepository.findByCompanyCodeAndNumber(
                simCard.getCompanyCode(), simCard.getNumber());
        PacketTraffic packetTraffic;
        if (optionalPaketTraffic.isEmpty()) {
            packetTraffic = new PacketTraffic();
        } else {
            packetTraffic = optionalPaketTraffic.get();
        }
        packetTraffic.setNumber(simCard.getNumber());
        packetTraffic.setCompanyCode(simCard.getCompanyCode());
        packetTraffic.setTrafficExpireDate(new Date(System.currentTimeMillis() + packet.getExpireDayMillis()));
        packetTraffic.setPacket(packet);
        packetTraffic.setAmount(packetTraffic.getAmount() + packet.getAmount());
        packetTrafficRepository.save(packetTraffic);
        simCard.setBalance(simCard.getBalance() - packet.getPrice());
        simCardRepository.save(simCard);

        return new ApiResponse("succesfully", true);


    }

    public ApiResponse getMyPacketTraffic(SimCard simCard, PacketType packetType) {

        Optional<PacketTraffic> optionalPacketTraffic =
                packetTrafficRepository.findByPacketTypeAndSimCard(packetType, simCard);
        if (optionalPacketTraffic.isEmpty())
            return new ApiResponse("not found ", false);

        return new ApiResponse("succes", true, optionalPacketTraffic.get());
    }


    public ApiResponse sendSms(SimCard simCard, String receiveCompanyCode, String receiveNumber) {
        if (!(simCard.getBalance() > 0))
            return new ApiResponse("aktiv emassiz", false);

        Optional<TariffTraffic> optionalTariffTraffic =
                tariffTrafficRepository.findBySimCardNumber(simCard.getSimCardNumber());
            TariffTraffic tariffTraffic = optionalTariffTraffic.get();
            if (tariffTraffic.getSms() >= 1) {
                ///////////////////////////////////////////
                tariffTraffic.setSms(tariffTraffic.getSms() - 1);
                tariffTrafficRepository.save(tariffTraffic);
                return new ApiResponse("yuborildi", true);
            } else {
                Optional<PacketTraffic> optionalPacketTraffic =
                        packetTrafficRepository.findByPacketTypeAndSimCard(PacketType.SMS, simCard);
                if (optionalPacketTraffic.isPresent()) {
                    PacketTraffic packetTraffic = optionalPacketTraffic.get();
                    if (packetTraffic.getAmount() >= 1) {
                        /////////////////////////////////////////////
                        packetTraffic.setAmount(packetTraffic.getAmount() - 1);
                        packetTrafficRepository.save(packetTraffic);
                        return new ApiResponse("yuborildi", true);
                    }
                }
                if (simCard.getBalance() > simCard.getTariff().getPriceOfSms()) {
                    /////////////////////////////////
                    simCard.setBalance(simCard.getBalance() - simCard.getTariff().getPriceOfSms());
                    simCardRepository.save(simCard);
                    return new ApiResponse("yuborildi", true);
                } else {
                    return new ApiResponse("hisobingizda mablag yetarli emas", false);
                }
        }
    }

    public ApiResponse call(SimCard simCard, String receiveCompanyCode, String receiveNumber) {

        if (!(simCard.getBalance() > 0))
            return new ApiResponse("aktiv emassiz", false);

        Optional<SimCard> receiveOptionalSimCard =
                simCardRepository.findByCompanyCodeAndNumber(receiveCompanyCode, receiveNumber);
        if (receiveOptionalSimCard.isEmpty())
            return new ApiResponse("not found", false);

        SimCard receiveSimCard = receiveOptionalSimCard.get();
        Optional<TariffTraffic> optionalTariffTraffic =
                tariffTrafficRepository.findBySimCardNumber(simCard.getSimCardNumber());
        TariffTraffic tariffTraffic = optionalTariffTraffic.get();

        Optional<CodesCompany> optionalCodesCompany = codesCompanyRepository.findByCode(receiveSimCard.getCompanyCode());
        if (optionalCodesCompany.isPresent()){
            if (tariffTraffic.getMinuteInNet() >= 1) {
                /////////////////////////
                return new ApiResponse("success", true);
            } else {
                Optional<PacketTraffic> optionalPacketTrafficMinuteInNet =
                        packetTrafficRepository.findByPacketTypeAndSimCard(PacketType.MINUTE_IN_NET, simCard);
                if (optionalPacketTrafficMinuteInNet.isPresent()) {
                    PacketTraffic packetTrafficMinuteInNet = optionalPacketTrafficMinuteInNet.get();
                    if (packetTrafficMinuteInNet.getAmount() >= 1) {
                        //////////////////////////////////
                        return new ApiResponse("success", true);
                    }
                }
                if (tariffTraffic.getMinuteOutNet() >= 1) {
                    /////////////////////////
                    return new ApiResponse("success", true);
                } else {
                    Optional<PacketTraffic> optionalPacketTrafficMinuteOutNet =
                            packetTrafficRepository.findByPacketTypeAndSimCard(PacketType.MINUTE_OUT_NET, simCard);
                    if (optionalPacketTrafficMinuteOutNet.isPresent()) {
                        PacketTraffic packetTrafficMinuteOutNet = optionalPacketTrafficMinuteOutNet.get();
                        if (packetTrafficMinuteOutNet.getAmount() >= 1) {
                            ////////////////////////////////
                            return new ApiResponse("success", true);
                        }
                    }
                    if (simCard.getBalance() > simCard.getTariff().getPriceOfMinInNet()) {
                        /////////////////////////////////////
                        return new ApiResponse("success", true);
                    }
                    else {
                        return new ApiResponse("hisobingizda mablag yetarli emas", false);
                    }
                }
            }
        }
        else {
            if (tariffTraffic.getMinuteOutNet() >= 1) {
                /////////////////////////
                return new ApiResponse("success", true);
            } else {
                Optional<PacketTraffic> optionalPacketTrafficMinuteOutNet =
                        packetTrafficRepository.findByPacketTypeAndSimCard(PacketType.MINUTE_OUT_NET, simCard);
                if (optionalPacketTrafficMinuteOutNet.isPresent()) {
                    PacketTraffic packetTrafficMinuteOutNet = optionalPacketTrafficMinuteOutNet.get();
                    if (packetTrafficMinuteOutNet.getAmount() >= 1) {
                        ////////////////////////////////
                        return new ApiResponse("success", true);
                    }
                }
                 if (simCard.getBalance() > simCard.getTariff().getPriceOfMinOutNet()) {
                    /////////////////////////////////////
                    return new ApiResponse("success", true);
                }
                else {
                    return new ApiResponse("hisobingizda mablag yetarli emas", false);
                }
            }
        }

}







}

