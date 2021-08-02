package uz.developer.communication_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.controller.TariffTrafficController;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.entity.TariffTraffic;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.TariffTrafficRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TariffTrafficService {

    @Autowired
    TariffTrafficRepository tariffTrafficRepository;

    public ApiResponse getById(Long id) {

        Optional<TariffTraffic> optionalTariffTraffic = tariffTrafficRepository.findById(id);
        return optionalTariffTraffic.map(
                tariffTraffic -> new ApiResponse("success ", true, tariffTraffic)).orElseGet(()
                -> new ApiResponse("not found ", false));
    }

    public ApiResponse getByCodeAndNumber(String companyCode,String number) {

        Optional<TariffTraffic> optionalTariffTraffic = tariffTrafficRepository.findBySimCard_CompanyCodeAndSimCard_Number(companyCode,number);
        return optionalTariffTraffic.map(
                tariffTraffic -> new ApiResponse("success ", true, tariffTraffic)).orElseGet(()
                -> new ApiResponse("not found", false));
    }

    public ApiResponse getAll() {
        List<TariffTraffic> list = tariffTrafficRepository.findAll();
        return new ApiResponse("natija",true,list);
    }
}

