package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.District;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.entity.ServiceCategory;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.ServiceCategoryRepository;

import java.util.Optional;

@Service
public class ServiceCategoryService {

    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;


    public ApiResponse add(ServiceCategory serviceCategory) {

        if (serviceCategoryRepository.existsByName(serviceCategory.getName()))
            return new ApiResponse("Already exist ServiceCategory",false);

            serviceCategoryRepository.save(serviceCategory);
            return new ApiResponse("ServiceCategory added ",true);
    }

    public ApiResponse getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<ServiceCategory> pages = serviceCategoryRepository.findAll(pageable);

        return new ApiResponse("success ",true,pages);
    }

    public ApiResponse getById(Integer id) {

        Optional<ServiceCategory> optionalServiceCategory = serviceCategoryRepository.findById(id);
        return optionalServiceCategory.map(
                region -> new ApiResponse("success ", true, region)).orElseGet(()
                -> new ApiResponse("not found ServiceCategory", false));
    }

    public ApiResponse edit(ServiceCategory serviceCategory, Integer id) {

        Optional<ServiceCategory> optionalServiceCategory = serviceCategoryRepository.findById(id);
        if (optionalServiceCategory.isEmpty())
            return new ApiResponse("Not Found ServiceCategory",false);

        if (serviceCategoryRepository.existsByName(serviceCategory.getName()))
            return new ApiResponse("Already exist ServiceCategory",false);

        serviceCategoryRepository.save(serviceCategory);
        return new ApiResponse("ServiceCategory added ",true);

    }



}
