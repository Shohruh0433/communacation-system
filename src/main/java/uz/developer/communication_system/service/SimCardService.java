package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
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
    CodesCompanyRepository codesCompanyRepository;
    @Autowired
    TariffRepository tariffRepository;
    @Autowired
    UserRepository userRepository;

    //nomer orqali bo'sh bo'lgan sim kartani qidirish
    public  ApiResponse getSearchSimCard(SimCardForSearchDto simCardForSearchDto) {
        List<SimCard> simCardList;
        if (simCardForSearchDto.getCompanyCode() == null){
            simCardList = simCardRepository.findAllByNumberContainsAndUserNull(
                    simCardForSearchDto.getNumber());
        }
        else {
            simCardList = simCardRepository.findAllByNumberContainsAndUserNullAndCompanyCode(
                    simCardForSearchDto.getNumber(), simCardForSearchDto.getCompanyCode());
        }
        return new ApiResponse("natija: ", true, simCardList);
    }
    public  ApiResponse getByNumberSearchSimCard(SimCardForSearchDto simCardForSearchDto) {

        Optional<SimCard> optionalSimCard = simCardRepository.findByCompanyCodeAndNumber(
                 simCardForSearchDto.getCompanyCode(),  simCardForSearchDto.getNumber());
        return optionalSimCard.map(simCard -> new ApiResponse(
       "natija: ", true, simCard)).orElseGet(() -> new ApiResponse("not found: ", false));
    }
    //sim kartaga buyurtma ya'ni simm carta sotib olish
    public ApiResponse simCardOrder(SimCardForOrderDto simCardForOrderDto) {
        Optional<SimCard> optionalSimCard = simCardRepository.findByCompanyCodeAndNumberAndUserNull(
                simCardForOrderDto.getCode(),  simCardForOrderDto.getNumber());
        if (optionalSimCard.isEmpty())
            return new ApiResponse("Sorry this sim card already buyed", false);
        Optional<CodesCompany> codesCompany = codesCompanyRepository.findByCompanyCode(simCardForOrderDto.getCode());
        if (codesCompany.isEmpty())
            return new ApiResponse("this code company not found", false);
        Optional<Tariff> optionalTariff = tariffRepository.findById(simCardForOrderDto.getTariff_id());
        if (optionalTariff.isEmpty())
            return new ApiResponse("this Tariff not found", false);
        ApiResponse apiResponse = getPassportBySeriesAndNumber(simCardForOrderDto.getPassportSeries(),
                simCardForOrderDto.getPassportNumber());
        if (!apiResponse.isSuccess())
            return apiResponse;
        User user = (User) apiResponse.getObject();
        userRepository.save(user);
        SimCard simCard = new SimCard();
        simCard.setActive(true);
        simCard.setCompanyCode(simCardForOrderDto.getCode());
        simCard.setUser(user);
        simCard.setNumber(simCardForOrderDto.getNumber());
        simCard.setTariff(optionalTariff.get());
        simCardRepository.save(simCard);
        return new ApiResponse("Successfully ordered", true);
    }
    //sim kartani blocklash
    public ApiResponse blockSimCard(SimCardForSearchDto simCardForSearchDto) {
        Optional<SimCard> optionalSimCard = simCardRepository.findByCompanyCodeAndNumber(
                simCardForSearchDto.getCompanyCode(), simCardForSearchDto.getNumber());
        if (optionalSimCard.isEmpty())
            return new ApiResponse("this sim card not found", false);
        SimCard simCard = optionalSimCard.get();
        simCard.setBlock(true);
        simCard.setActive(false);
        simCardRepository.save(simCard);
        return new ApiResponse("this sim card successfully blocked", true);
    }
    //shu kompaniyaga tegishli barcha egasi yo'q sim kartalarni chiqarish
    public ApiResponse getAllUserNullSimCards() {
        return new ApiResponse("result", true, simCardRepository.findAllByUserIsNull());
    }
    //shu userga tegishli sim card larni ko'rish
    public ApiResponse getAllByUserPassport(String series, String number) {
        return new ApiResponse("result", true,
                simCardRepository.findAllByUser_PassportSeriesAndUser_PassportNumber(series, number));
    }
    //yangi nomerlarni bazaga kiritiish ya'ni egasi yoq nomerlarni
    public ApiResponse add(SimCardDto simCardDto) {
        Optional<SimCard> optionalSimCard = simCardRepository.findByCompanyCodeAndNumber(simCardDto.getCode(), simCardDto.getNumber());
        if (optionalSimCard.isEmpty()) {
            Optional<CodesCompany> byCode = codesCompanyRepository.findByCompanyCode(simCardDto.getCode());
            if (byCode.isEmpty())
                return new ApiResponse("Sorry! this Company code not found", false);
            SimCard simCard = new SimCard();
            simCard.setActive(false);
            simCard.setCompanyCode(simCardDto.getCode());
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
        user.setPassportSeries(passport.getPassportSeria());
        return new ApiResponse("successfully", true,user);
    }
}
