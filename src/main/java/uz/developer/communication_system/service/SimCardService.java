package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.developer.communication_system.entity.CodesCompany;
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.entity.Tariff;
import uz.developer.communication_system.entity.User;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.SimCardDto;
import uz.developer.communication_system.payload.SimCardForOrderDto;
import uz.developer.communication_system.payload.SimCardForSearchDto;
import uz.developer.communication_system.payload.model.Passport;
import uz.developer.communication_system.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public  class SimCardService {

    @Autowired
    SimCardRepository simCardRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CodesCompanyRepository codesCompanyRepository;
    @Autowired
    TariffRepository tariffRepository;
    @Autowired
    UserRepository userRepository;


    //nomer orqali bo'sh bo'lgan sim kartani qidirish
    public  ApiResponse getSearchSimCard(SimCardForSearchDto simCardForSearchDto) {
        List<SimCard> allByCodeOrNumberContainsAndUserNull = simCardRepository.findAllByCodeOrNumberContainsAndUserNull(
                simCardForSearchDto.getCode(), simCardForSearchDto.getNomer());
        return new ApiResponse("natija: ", true, allByCodeOrNumberContainsAndUserNull);
    }


    //sim kartaga buyurtma ya'ni simm carta sotib olish
    public ApiResponse simCardOrder(SimCardForOrderDto simCardForOrderDto) {
        Optional<SimCard> optionalSimCard = simCardRepository.findByCodeAndNumberAndUserNull(
                simCardForOrderDto.getCode(),  simCardForOrderDto.getNomer());
        if (optionalSimCard.isEmpty())
            return new ApiResponse("Sorry this sim card already buyed", false);

        Optional<CodesCompany> codesCompany = codesCompanyRepository.findByCode(simCardForOrderDto.getCode());
        if (codesCompany.isEmpty())
            return new ApiResponse("this code company not found", false);

        Optional<Tariff> optionalTariff = tariffRepository.findById(simCardForOrderDto.getTariff_id());
        if (optionalTariff.isEmpty())
            return new ApiResponse("this Tariff not found", false);

        ApiResponse apiResponse = getPassportBySeriesAndNumber(simCardForOrderDto.getPassportSeria(),
                simCardForOrderDto.getPassportNumber());
        if (!apiResponse.isSuccess())
            return apiResponse;

        User user = (User) apiResponse.getObject();
        userRepository.save(user);

        SimCard simCard = new SimCard();
        simCard.setActive(true);
        simCard.setCode(simCardForOrderDto.getCode());
        simCard.setUser(user);
        simCard.setCompany(codesCompany.get().getCompany());
        simCard.setNumber(simCardForOrderDto.getNomer());
        simCard.setTariff(optionalTariff.get());
        simCardRepository.save(simCard);
        return new ApiResponse("Successfully ordered", true);

    }


    //sim kartani blocklash
    public ApiResponse blockSimCard(SimCardForSearchDto simCardForSearchDto) {
        Optional<SimCard> optionalSimCard = simCardRepository.findByCodeAndNumber(
                simCardForSearchDto.getCode(), simCardForSearchDto.getNomer());
        if (optionalSimCard.isEmpty())
            return new ApiResponse("this sim card not found", false);
        SimCard simCard = optionalSimCard.get();
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
        return new ApiResponse("result", true,
                simCardRepository.findAllByUser_PassportSeriyaAndUser_PassportNumber(seria, number));
    }


    //yangi nomerlarni bazaga kiritiish ya'ni egasi yoq nomerlarni
    public ApiResponse add(SimCardDto simCardDto) {
        Optional<SimCard> optionalSimCard = simCardRepository.findByCodeAndNumber(simCardDto.getCode(), simCardDto.getNumber());
        if (optionalSimCard.isEmpty()) {
            Optional<CodesCompany> byCode = codesCompanyRepository.findByCode(simCardDto.getCode());
            if (byCode.isEmpty())
                return new ApiResponse("Sorry! this code company not found", false);

            SimCard simCard = new SimCard();
            simCard.setActive(false);
            simCard.setCompany(byCode.get().getCompany());
            simCard.setCode(simCardDto.getCode());
            simCard.setNumber(simCardDto.getNumber());
            simCard.setTariff(null);
            simCard.setBalance(0);
            simCard.setBlock(true);
            simCard.setPaketTraffic(null);
            simCard.setUser(null);
            simCardRepository.save(simCard);
            return new ApiResponse("Successfully saved", true);
        } else {
            return new ApiResponse("Sorry! This number saved", false);
        }

    }


    public ApiResponse getPassportBySeriesAndNumber(String series, String number) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = "http://localhost:8081/api/passport/getPassportBySeriaAndNumber?seria=" + series + "&number=" + number;

        ResponseEntity<Passport> exchange = restTemplate.exchange
                (url, HttpMethod.GET, entity, Passport.class);

        Passport passport = exchange.getBody();
        if (passport == null)
            return new ApiResponse("passport not found", false);

        User user = new User();
        user.setBirthDate(passport.getBirthDate());
        user.setFirstName(passport.getFirstName());
        user.setLegal(passport.isLegal());
        user.setLastName(passport.getLastName());
        user.setPassportNumber(passport.getPassportNumber());
        user.setPassportSeriya(passport.getPassportSeria());

        return new ApiResponse("successfully", true,user);

    }



}
