package uz.developer.communication_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.Branch;
import uz.developer.communication_system.entity.District;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.BranchDto;
import uz.developer.communication_system.repository.BranchRepository;
import uz.developer.communication_system.repository.DistrictRepository;

import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    BranchRepository branchRepository;
    @Autowired
    DistrictRepository  districtRepository;


    public ApiResponse add(BranchDto branchDto) {
        try {

            if (districtRepository.existsByName(branchDto.getName()))
                return new ApiResponse("Already exist Branch",false);

            Branch branch = new Branch();

            Optional<District> optionalDistrict = districtRepository.findById(branchDto.getDistrictId());
            if (optionalDistrict.isEmpty())
                return new ApiResponse("Not found District",false);

            branch.setName(branchDto.getName());
            branch.setDistrict(optionalDistrict.get());
            branchRepository.save(branch);
            return new ApiResponse("Branch added ",true);
        }catch (Exception e){
            return new ApiResponse("Branch did not added",false);
        }

    }

    public ApiResponse getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Branch> pages = branchRepository.findAll(pageable);

        return new ApiResponse("success ",true,pages);

    }

    public ApiResponse getById(Integer id) {

        Optional<Branch> optionalCompany = branchRepository.findById(id);
        return optionalCompany.map(
                region -> new ApiResponse("success ", true, region)).orElseGet(()
                -> new ApiResponse("not found Branch", false));
    }

    public ApiResponse edit(BranchDto branchDto, Integer id) {

        try {

            Optional<Branch> optionalBranch = branchRepository.findById(id);
            if (optionalBranch.isEmpty())
                return new ApiResponse("Not found Company",false);
            Branch branch = optionalBranch.get();

            Optional<District> optionalDistrict = districtRepository.findById(branchDto.getDistrictId());
            if (optionalDistrict.isEmpty())
                return new ApiResponse("Not found  District",false);

            branch.setName(branchDto.getName());
            branch.setActive(branchDto.isActive());
            branch.setDistrict(optionalDistrict.get());
            branchRepository.save(branch);
            return new ApiResponse("Branch added ",true);
        }catch (Exception e){
            return new ApiResponse("Branch did not added",false);
        }

    }


    public ApiResponse getByDistrict(Integer districtId, int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Branch> pages = branchRepository.findAllByDistrict_Id(districtId,pageable);

        return new ApiResponse("success ",true,pages);

    }

    public ApiResponse getByParentCompany(Integer branchId, int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Branch> pages = branchRepository.findAllByCompanyId(branchId,pageable);

        return new ApiResponse("success ",true,pages);

    }

    public ApiResponse getByRegionId(Integer regionId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Branch> pages = branchRepository.findAllByDistrict_Region_Id(regionId,pageable);
        return new ApiResponse("success ",true,pages);
    }
}
