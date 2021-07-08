package uz.developer.communication_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.Company;
import uz.developer.communication_system.entity.District;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.CompanyDto;
import uz.developer.communication_system.repository.CompanyRepository;
import uz.developer.communication_system.repository.DistrictRepository;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DistrictRepository  districtRepository;


    public ApiResponse add(CompanyDto companyDto) {
        try {

            if (districtRepository.existsByName(companyDto.getName()))
                return new ApiResponse("Already exist Company",false);

            Company company = new Company();
            if (companyDto.getCompanyId() != null){
            Optional<Company> optionalCompany = companyRepository.findById(companyDto.getCompanyId());
            if (optionalCompany.isEmpty())
                return new ApiResponse("Not found Company",false);

                company.setCompany(optionalCompany.get());
            }
            Optional<District> optionalDistrict = districtRepository.findById(companyDto.getDistrictId());
            if (optionalDistrict.isEmpty())
                return new ApiResponse("Not found District",false);

            company.setName(companyDto.getName());
            company.setDistrict(optionalDistrict.get());
            companyRepository.save(company);
            return new ApiResponse("Company added ",true);
        }catch (Exception e){
            return new ApiResponse("Company did not added",false);
        }

    }

    public ApiResponse getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Company> pages = companyRepository.findAll(pageable);

        return new ApiResponse("success ",true,pages);

    }

    public ApiResponse getById(Integer id) {

        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.map(
                region -> new ApiResponse("success ", true, region)).orElseGet(()
                -> new ApiResponse("not found Company", false));
    }

    public ApiResponse edit(CompanyDto companyDto, Integer id) {

        try {

            Optional<Company> optionalCompany1 = companyRepository.findById(id);
            if (optionalCompany1.isEmpty())
                return new ApiResponse("Not found Company",false);

            Company company = new Company();
            if (companyDto.getCompanyId() != null){
                Optional<Company> optionalCompany = companyRepository.findById(companyDto.getCompanyId());
                if (optionalCompany.isEmpty())
                    return new ApiResponse("Not found  Company",false);

                company.setCompany(optionalCompany.get());
            }
            Optional<District> optionalDistrict = districtRepository.findById(companyDto.getDistrictId());
            if (optionalDistrict.isEmpty())
                return new ApiResponse("Not found  District",false);

            company.setName(companyDto.getName());
            company.setDistrict(optionalDistrict.get());
            companyRepository.save(company);
            return new ApiResponse("Company added ",true);
        }catch (Exception e){
            return new ApiResponse("Company did not added",false);
        }

    }


}
