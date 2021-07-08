package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.developer.communication_system.entity.CodesCompany;
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.entity.Tariff;
import uz.developer.communication_system.payload.ApiResponse;
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




    public ApiResponse getSearchSimCard(SimCardForSearchDto simCardForSearchDto){
        List<SimCard> allByCodeOrNumberContainsAndUserNull = simCardRepository.findAllByCodeOrNumberContainsAndUserNull(simCardForSearchDto.getCode(), simCardForSearchDto.getNomer());
        return new ApiResponse("natija: ",true,allByCodeOrNumberContainsAndUserNull);
    }

    public ApiResponse simCardOrder(SimCardForOrderDto simCardForOrderDto){
        Optional<SimCard> optionalSimCard = simCardRepository.findByCodeAndNumberAndUserNull(simCardForOrderDto.getCode(), simCardForOrderDto.getNomer());
        if (!optionalSimCard.isPresent())
            return new ApiResponse("Bunday sim karta olingan booshqa variant ni ko'ring",false);
        Passport passport = getPassportBySeriaAndNumber(simCardForOrderDto.getPassportSeria(), simCardForOrderDto.getPassportNumber());
        if (passport==null)
            return new ApiResponse("passport not found",false);
        CodesCompany codesCompany = codesCompanyRepository.findByCode(simCardForOrderDto.getCode());
        if (codesCompany==null)
            return new ApiResponse("this code company not found",false);
        Optional<Tariff> optionalTariff = tariffRepository.findById(simCardForOrderDto.getTariff_id());
        if (!optionalTariff.isPresent())
            return new ApiResponse("Tariff not found",false);


        SimCard simCard=new SimCard();
        simCard.setActive(true);
        simCard.setBalance(0);
        simCard.setBlock(false);
        simCard.setCode(simCardForOrderDto.getCode());

        simCard.setCompany(codesCompany.getCompany());
        simCard.setNumber(simCardForOrderDto.getNomer());
        simCard.setTariff(optionalTariff.get());
        simCardRepository.save(simCard);
        return new ApiResponse("Happy ! Succesfully ordered",true);

    }

    public Passport getPassportBySeriaAndNumber(String seria, String number) {

        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url="http://localhost:8081/api/passport/getPassportBySeriaAndNumber?seria="+seria+"&number="+number;

        ResponseEntity<Passport> exchange = restTemplate.exchange
                (url, HttpMethod.GET,entity,
                        Passport.class);
        return exchange.getBody();

    }




}