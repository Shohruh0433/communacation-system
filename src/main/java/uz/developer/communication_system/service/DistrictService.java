package uz.developer.communication_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.District;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.DistrictDto;
import uz.developer.communication_system.repository.DistrictRepository;
import uz.developer.communication_system.repository.RegionRepository;

import java.util.Optional;

@Service
public class DistrictService {


    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    RegionRepository regionRepository;


    public ApiResponse add(DistrictDto districtDto) {
        try {
            District district = new District();
            district.setName(districtDto.getName());
            Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
            if (optionalRegion.isEmpty())
                return new ApiResponse("Not found Region",false);

            district.setRegion(optionalRegion.get());
            districtRepository.save(district);
            return new ApiResponse("District added ",true);
        }catch (Exception e){
            return new ApiResponse("District did not added",false);
        }

    }

    public ApiResponse getAll() {

        Pageable pageable = PageRequest.of(0,10);
        Page<District> page = districtRepository.findAll(pageable);

        return new ApiResponse("success ",true,page);
    }

    public ApiResponse getById(Integer id) {

        Optional<District> optionalDistrict = districtRepository.findById(id);
        return optionalDistrict.map(
                region -> new ApiResponse("success ", true, region)).orElseGet(()
                -> new ApiResponse("not found District", false));
    }

    public ApiResponse edit(DistrictDto districtDto, Integer id) {

        try {
            Optional<District> optionalDistrict = districtRepository.findById(id);
            if (optionalDistrict.isEmpty())
                return new ApiResponse("Not found District",false);

            Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
            if (optionalRegion.isEmpty())
                return new ApiResponse("Not found Region",false);

            District district = new District();
            district.setName(districtDto.getName());
            district.setRegion(optionalRegion.get());
            districtRepository.save(district);
            return new ApiResponse("District edited ",true);
        }catch (Exception e){
            return new ApiResponse("District did not edited",false);
        }
    }

    public ApiResponse delete(Integer id) {

        try{
            districtRepository.deleteById(id);
            return new ApiResponse("District deleted ",true);
        }catch (Exception e){
            return new ApiResponse("Not found District ",false);
        }
    }
}
