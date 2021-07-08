package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    public ApiResponse add(Region region) {

        try {
            regionRepository.save(region);
            return new ApiResponse("Region added ",true);
        }catch (Exception e){
            return new ApiResponse("Already exist Region",false);
        }

    }

    public ApiResponse getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Region> pages = regionRepository.findAll(pageable);

        return new ApiResponse("success ",true,pages);
    }

    public ApiResponse getById(Integer id) {

        Optional<Region> optionalRegion = regionRepository.findById(id);
        return optionalRegion.map(
                region -> new ApiResponse("success ", true, region)).orElseGet(()
                -> new ApiResponse("not found Region", false));

    }

    public ApiResponse edit(Region region, Integer id) {

        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isEmpty())
            return new ApiResponse("Not fount Region",false);

        regionRepository.save(region);
            return new ApiResponse("Region edited ",true);


    }

    public ApiResponse delete(Integer id) {

        try{
            regionRepository.deleteById(id);
            return new ApiResponse("Region deleted ",true);
        }catch (Exception e){
            return new ApiResponse("Not found Region ",false);
        }

    }

}
