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

import java.util.Optional;

@Service
public class TariffTrafficService {

    @Autowired
    TariffTrafficRepository tariffTrafficRepository;


    public ApiResponse add(TariffTraffic tariffTraffic) {

        try {
            tariffTrafficRepository.save(tariffTraffic);
            return new ApiResponse("TariffTraffic added ", true);
        }catch (Exception e){
            return new ApiResponse("did not added TariffTraffic", false);
        }

    }

    public ApiResponse getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<TariffTraffic> pages = tariffTrafficRepository.findAll(pageable);

        return new ApiResponse("success ",true,pages);
    }


    public ApiResponse getById(Long id) {

        Optional<TariffTraffic> optionalTariffTraffic = tariffTrafficRepository.findById(id);
        return optionalTariffTraffic.map(
                region -> new ApiResponse("success ", true, region)).orElseGet(()
                -> new ApiResponse("not found TariffTraffic", false));
    }

    public ApiResponse edit(TariffTraffic tariffTraffic, Long id) {

        try {
            Optional<TariffTraffic> optionalTariffTraffic = tariffTrafficRepository.findById(id);
            if (optionalTariffTraffic.isEmpty())
                return new ApiResponse("TariffTraffic not found ", false);

            tariffTrafficRepository.save(tariffTraffic);
            return new ApiResponse("TariffTraffic edited ", true);
        }catch (Exception e){
            return new ApiResponse("did not edited TariffTraffic", false);
        }

    }


}

