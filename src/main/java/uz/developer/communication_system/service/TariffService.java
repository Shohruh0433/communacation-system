package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.Branch;
import uz.developer.communication_system.entity.Tariff;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.TariffDto;
import uz.developer.communication_system.repository.BranchRepository;
import uz.developer.communication_system.repository.TariffRepository;

import java.util.Optional;

@Service
public class TariffService {

    @Autowired
    TariffRepository tariffRepository;
    @Autowired
    BranchRepository branchRepository;


    public ApiResponse add(TariffDto tariffDto) {

        try {
            if (tariffRepository.existsByName(tariffDto.getName()))
                return new ApiResponse("Already exist this name",false);

            Tariff tariff = new Tariff();
         tariff.setExpireDay(tariffDto.getExpireDay());
         tariff.setLegal(tariffDto.isLegal());
         tariff.setName(tariffDto.getName());
         tariff.setMinuteInNet(tariffDto.getMinuteInNet());
         tariff.setMinuteOutNet(tariffDto.getMinuteOutNet());
         tariff.setNetLimitForInstagram(tariffDto.getNetLimitForInstagram());
         tariff.setNetLimitForTelegram(tariffDto.getNetLimitForTelegram());
         tariff.setNetLimitAll(tariffDto.getNetLimitAll());
         tariff.setNetLimitForYoutube(tariffDto.getNetLimitForYoutube());
         tariff.setPriceOfDay(tariffDto.getPriceOfDay());
         tariff.setPriceOfMinInNet(tariffDto.getPriceOfMinInNet());
         tariff.setPriceOfMinOutNet(tariffDto.getPriceOfMinOutNet());
         tariff.setPriceOfMonth(tariffDto.getPriceOfMonth());
         tariff.setPriceOfNetAll(tariffDto.getPriceOfNetAll());
         tariff.setSms(tariffDto.getSms());
         tariff.setPriceOfSms(tariffDto.getPriceOfSms());
         tariff.setTransitionPrice(tariffDto.getTransitionPrice());

            return new ApiResponse("Tariff added ",true);
        }catch (Exception e){
            return new ApiResponse("Tariff did not added",false);
        }

    }

    public ApiResponse getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Tariff> pages = tariffRepository.findAll(pageable);

        return new ApiResponse("success ",true,pages);

    }

    public ApiResponse getById(Long id) {

        Optional<Tariff> optionalTariff = tariffRepository.findById(id);
        return optionalTariff.map(
                region -> new ApiResponse("success ", true, region)).orElseGet(()
                -> new ApiResponse("not found Tariff", false));

    }

    public ApiResponse edit(TariffDto tariffDto, Long id) {

        try {

            Optional<Tariff> optionalTariff = tariffRepository.findById(id);
            if (optionalTariff.isEmpty())
                return new ApiResponse("Not found Tariff",false);

            if (tariffRepository.existsByName(tariffDto.getName()))
                return new ApiResponse("Already exist this name",false);

            Tariff tariff = optionalTariff.get();
            tariff.setExpireDay(tariffDto.getExpireDay());
            tariff.setLegal(tariffDto.isLegal());
            tariff.setName(tariffDto.getName());
            tariff.setMinuteInNet(tariffDto.getMinuteInNet());
            tariff.setMinuteOutNet(tariffDto.getMinuteOutNet());
            tariff.setNetLimitForInstagram(tariffDto.getNetLimitForInstagram());
            tariff.setNetLimitForTelegram(tariffDto.getNetLimitForTelegram());
            tariff.setNetLimitAll(tariffDto.getNetLimitAll());
            tariff.setNetLimitForYoutube(tariffDto.getNetLimitForYoutube());
            tariff.setPriceOfDay(tariffDto.getPriceOfDay());
            tariff.setPriceOfMinInNet(tariffDto.getPriceOfMinInNet());
            tariff.setPriceOfMinOutNet(tariffDto.getPriceOfMinOutNet());
            tariff.setPriceOfMonth(tariffDto.getPriceOfMonth());
            tariff.setPriceOfNetAll(tariffDto.getPriceOfNetAll());
            tariff.setSms(tariffDto.getSms());
            tariff.setTransitionPrice(tariffDto.getTransitionPrice());

            return new ApiResponse("Tariff edited ",true);
        }catch (Exception e){
            return new ApiResponse("Tariff did not edited",false);
        }

    }

    public ApiResponse delete(Long id) {

        Optional<Tariff> optionalTariff = tariffRepository.findById(id);
        if (optionalTariff.isEmpty())
            return new ApiResponse("Not found tariff",false);

        Tariff tariff = optionalTariff.get();
        tariff.setActive(false);
        return new ApiResponse("Tariff deleted",true);

    }

    public ApiResponse getByActive(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Tariff> pages = tariffRepository.findAllByActiveIsTrue(pageable);

        return new ApiResponse("success ",true,pages);
    }

    public ApiResponse getByDeleted(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Tariff> pages = tariffRepository.findAllByActiveIsFalse(pageable);

        return new ApiResponse("success ",true,pages);

    }

    public ApiResponse getByLegal(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Tariff> pages = tariffRepository.findAllByLegalIsTrue(pageable);

        return new ApiResponse("success ",true,pages);
    }

    public ApiResponse getByPhysical(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Tariff> pages = tariffRepository.findAllByLegalIsFalse(pageable);

        return new ApiResponse("success ",true,pages);
    }


}
