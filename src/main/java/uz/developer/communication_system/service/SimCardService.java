package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.developer.communication_system.entity.CodesCompany;
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.entity.Tariff;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.SimCardDto;
import uz.developer.communication_system.payload.SimCardForOrderDto;
import uz.developer.communication_system.payload.SimCardForSearchDto;
import uz.developer.communication_system.payload.model.Passport;
import uz.developer.communication_system.repository.CodesCompanyRepository;
import uz.developer.communication_system.repository.CompanyRepository;
import uz.developer.communication_system.repository.SimCardRepository;
import uz.developer.communication_system.repository.TariffRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SimCardService {

    @Autowired
    SimCardRepository simCardRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CodesCompanyRepository codesCompanyRepository;

    @Autowired
    TariffRepository tariffRepository;


    //nomer orqali bo'sh bo'lgan sim kartani qidirish
    public ApiResponse getSearchSimCard(SimCardForSearchDto simCardForSearchDto) {
        List<SimCard> allByCodeOrNumberContainsAndUserNull = simCardRepository.findAllByCodeOrNumberContainsAndUserNull(simCardForSearchDto.getCode(), simCardForSearchDto.getNomer());
        return new ApiResponse("natija: ", true, allByCodeOrNumberContainsAndUserNull);
    }


    //sim kartaga buyurtma ya'ni simm carta sotib olish
    public ApiResponse simCardOrder(SimCardForOrderDto simCardForOrderDto) {
        Optional<SimCard> optionalSimCard = simCardRepository.findByCodeAndNumberAndUserNull(simCardForOrderDto.getCode(), simCardForOrderDto.getNomer());
        if (optionalSimCard.isEmpty())
            return new ApiResponse("Sorry this sim card already buyed", false);
        Passport passport = getPassportBySeriaAndNumber(simCardForOrderDto.getPassportSeria(), simCardForOrderDto.getPassportNumber());
        if (passport == null)
            return new ApiResponse("passport not found", false);
        Optional<CodesCompany> codesCompany = codesCompanyRepository.findByCode(simCardForOrderDto.getCode());
        if (!codesCompany.isPresent())
            return new ApiResponse("this code company not found", false);
        Optional<Tariff> optionalTariff = tariffRepository.findById(simCardForOrderDto.getTariff_id());
        if (optionalTariff.isEmpty())
            return new ApiResponse("this Tariff not found", false);


        SimCard simCard = new SimCard();
        simCard.setActive(true);
        simCard.setBalance(0);
        simCard.setBlock(false);
        simCard.setCode(simCardForOrderDto.getCode());

        simCard.setCompany(codesCompany.get().getCompany());
        simCard.setNumber(simCardForOrderDto.getNomer());
        simCard.setTariff(optionalTariff.get());
        simCardRepository.save(simCard);
        return new ApiResponse("Happy ! Succesfully ordered", true);

    }


    //sim kartani blocklash
    public ApiResponse blockSimCard(SimCardForSearchDto simCardForSearchDto) {
        SimCard simCard = simCardRepository.findByCodeAndNumber(simCardForSearchDto.getCode(), simCardForSearchDto.getNomer());
        if (simCard == null)
            return new ApiResponse("this sim card not found", false);
        simCard.setBlock(true);
        simCard.setActive(false);
        simCardRepository.save(simCard);
        return new ApiResponse("this sim card successfully blocked", true);
    }


    //shu kompaniyaga tegishli barcha egasi yo'q sim kartalarni chiqarish
    public ApiResponse getAllUserNullSimcards(int companyId) {
        return new ApiResponse("result", true, simCardRepository.findAllByUserIsNullAndCompany_Id(companyId));
    }


    //shu userga tegishli sim card larni ko'rish
    public ApiResponse getAllByUserPassport(String seria, String number) {
        return new ApiResponse("result", true, simCardRepository.findAllByUser_PassportSeriyaAndUser_PassportNumber(seria, number));
    }


    //yangi nomerlarni bazaga kiritiish ya'ni egasi yoq nomerlarni
    public ApiResponse add(SimCardDto simCardDto) {
        SimCard simCard = simCardRepository.findByCodeAndNumber(simCardDto.getCode(), simCardDto.getNomer());
        if (simCard != null)
            return new ApiResponse("Sorry! This number saved", false);

        Optional<CodesCompany> byCode = codesCompanyRepository.findByCode(simCardDto.getCode());
        if (!byCode.isPresent())
            return new ApiResponse("Sorry! this code company not found", false);

        SimCard simCard1 = new SimCard();
        simCard1.setActive(false);
        simCard1.setCompany(byCode.get().getCompany());
        simCard1.setCode(simCardDto.getCode());
        simCard1.setNumber(simCardDto.getNomer());
        simCard1.setTariff(null);
        simCard1.setBalance(0);
        simCard1.setBlock(true);
        simCard1.setPaketTraffic(null);
        simCard1.setUser(null);
        simCardRepository.save(simCard1);
        return new ApiResponse("Successfully saved", true);

    }


    public Passport getPassportBySeriaAndNumber(String seria, String number) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = "http://localhost:8081/api/passport/getPassportBySeriaAndNumber?seria=" + seria + "&number=" + number;

        ResponseEntity<Passport> exchange = restTemplate.exchange
                (url, HttpMethod.GET, entity,
                        Passport.class);
        return exchange.getBody();

    }



}
