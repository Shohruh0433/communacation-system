package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.Branch;
import uz.developer.communication_system.entity.UssdCode;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.BranchRepository;
import uz.developer.communication_system.repository.UssdCodeRepository;

import java.util.Optional;

@Service
public class UssdCodeService {

    @Autowired
    UssdCodeRepository ussdCodeRepository;
    @Autowired
    BranchRepository branchRepository;


    public ApiResponse add(UssdCode ussdCode) {
        try {
            if (ussdCodeRepository.existsByCode(ussdCode.getCode()))
                return new ApiResponse("Already exist Code", false);

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

    public ApiResponse edit(UssdCode ussdCode, Integer id) {

        try {
            Optional<UssdCode> optionalUssdCode = ussdCodeRepository.findById(id);
            if (optionalUssdCode.isEmpty())
                return new ApiResponse("Not found Ussd Code", false);

            if (ussdCodeRepository.existsByCode(ussdCode.getCode()))
                return new ApiResponse("Already exist Code and Company", false);

            ussdCodeRepository.save(ussdCode);
            return new ApiResponse("UssdCode edited ", true);
        }catch (Exception e){
            return new ApiResponse("did not added edited", false);
        }
    }

}
