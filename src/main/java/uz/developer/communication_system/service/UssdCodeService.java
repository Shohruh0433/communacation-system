package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.Company;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.entity.UssdCode;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.UssdCodeDto;
import uz.developer.communication_system.repository.CompanyRepository;
import uz.developer.communication_system.repository.UssdCodeRepository;

import java.util.Optional;

@Service
public class UssdCodeService {

    @Autowired
    UssdCodeRepository ussdCodeRepository;
    @Autowired
    CompanyRepository companyRepository;


    public ApiResponse add(UssdCodeDto ussdCodeDto) {
        try {
            if (ussdCodeRepository.existsByCodeAndCompany_Id(ussdCodeDto.getCode(),ussdCodeDto.getCompanyId()))
                return new ApiResponse("Already exist Code and Company", false);

            Optional<Company> optionalCompany = companyRepository.findById(ussdCodeDto.getCompanyId());
            if (optionalCompany.isEmpty())
                return new ApiResponse("Not found Company", false);

            UssdCode ussdCode = new UssdCode();
            ussdCode.setCode(ussdCodeDto.getCode());
            ussdCode.setDescription(ussdCode.getDescription());

            ussdCodeRepository.save(ussdCode);
            return new ApiResponse("UssdCode added ", true);
        }catch (Exception e){
            return new ApiResponse("did not added UssdCode", false);
        }

    }

    public ApiResponse getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<UssdCode> pages = ussdCodeRepository.findAll(pageable);

        return new ApiResponse("success ",true,pages);
    }

    public ApiResponse getById(Integer id) {

        Optional<UssdCode> optionalRegion = ussdCodeRepository.findById(id);
        return optionalRegion.map(
                region -> new ApiResponse("success ", true, region)).orElseGet(()
                -> new ApiResponse("not found UssdCode", false));
    }

    public ApiResponse edit(UssdCodeDto ussdCodeDto, Integer id) {

        try {
            Optional<UssdCode> optionalUssdCode = ussdCodeRepository.findById(id);
            if (optionalUssdCode.isEmpty())
                return new ApiResponse("Not found Ussd Code", false);

            if (ussdCodeRepository.existsByCodeAndCompany_Id(ussdCodeDto.getCode(),ussdCodeDto.getCompanyId()))
                return new ApiResponse("Already exist Code and Company", false);

            Optional<Company> optionalCompany = companyRepository.findById(ussdCodeDto.getCompanyId());
            if (optionalCompany.isEmpty())
                return new ApiResponse("Not found Company", false);

            UssdCode ussdCode = optionalUssdCode.get();
            ussdCode.setCode(ussdCodeDto.getCode());
            ussdCode.setDescription(ussdCode.getDescription());

            ussdCodeRepository.save(ussdCode);
            return new ApiResponse("UssdCode edited ", true);
        }catch (Exception e){
            return new ApiResponse("did not added edited", false);
        }
    }

}
