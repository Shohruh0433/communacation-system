package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.Company;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.entity.ServiceCategory;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.ServiceDto;
import uz.developer.communication_system.repository.CompanyRepository;
import uz.developer.communication_system.repository.ServiceCategoryRepository;
import uz.developer.communication_system.repository.ServiceRepository;

import java.util.Optional;

@Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;
    @Autowired
    CompanyRepository companyRepository;


    public ApiResponse add(ServiceDto serviceDto) {

        try {
            if (serviceRepository.existsByName(serviceDto.getName()))
                return new ApiResponse("Already exist Service", false);

            Optional<ServiceCategory> optionalServiceCategory =
                    serviceCategoryRepository.findById(serviceDto.getServiceCategoryId());
            if (optionalServiceCategory.isEmpty())
                return new ApiResponse("Not Found Service Category", false);

            Optional<Company> optionalCompany =
                    companyRepository.findById(serviceDto.getCompanyId());
            if (optionalCompany.isEmpty())
                return new ApiResponse("Not Found Company", false);

            uz.developer.communication_system.entity.Service service=new uz.developer.communication_system.entity.Service();
            service.setName(serviceDto.getName());
            service.setCompany(optionalCompany.get());
            service.setServiceCode(serviceDto.getServiceCode());
            service.setDescription(serviceDto.getDescription());
            service.setPriceOfDay(serviceDto.getPriceOfDay());
            service.setPriceOfMonth(serviceDto.getPriceOfMonth());

                    serviceRepository.save(service);
            return new ApiResponse("Service added ", true);
        }catch (Exception e){
            return new ApiResponse("did not added Service", false);
        }

    }

    public ApiResponse getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<uz.developer.communication_system.entity.Service> pages = serviceRepository.findAll(pageable);

        return new ApiResponse("success ",true,pages);
    }

    public ApiResponse getById(Integer id) {

        Optional<uz.developer.communication_system.entity.Service> optionalService = serviceRepository.findById(id);
        return optionalService.map(
                region -> new ApiResponse("success ", true, region)).orElseGet(()
                -> new ApiResponse("not found Service", false));
    }

    public ApiResponse edit(ServiceDto serviceDto, Integer id) {

        try {

            Optional<uz.developer.communication_system.entity.Service> optionalService = serviceRepository.findById(id);
            if (optionalService.isEmpty())
                return new ApiResponse("Not Found Service", false);

            if (serviceRepository.existsByName(serviceDto.getName()))
                return new ApiResponse("Already exist  Service", false);

            Optional<ServiceCategory> optionalServiceCategory =
                    serviceCategoryRepository.findById(serviceDto.getServiceCategoryId());
            if (optionalServiceCategory.isEmpty())
                return new ApiResponse("Not Found  Service Category", false);

            Optional<Company> optionalCompany =
                    companyRepository.findById(serviceDto.getCompanyId());
            if (optionalCompany.isEmpty())
                return new ApiResponse("Not Found Company", false);

            uz.developer.communication_system.entity.Service service=optionalService.get();
            service.setName(serviceDto.getName());
            service.setCompany(optionalCompany.get());
            service.setActive(serviceDto.isActive());
            service.setServiceCode(serviceDto.getServiceCode());
            service.setDescription(serviceDto.getDescription());
            service.setPriceOfDay(serviceDto.getPriceOfDay());
            service.setPriceOfMonth(serviceDto.getPriceOfMonth());

            serviceRepository.save(service);
            return new ApiResponse("Service  edited ", true);
        }catch (Exception e){
            return new ApiResponse("did not edited Service", false);
        }

    }

    public ApiResponse getByCompany(Integer companyId, int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<uz.developer.communication_system.entity.Service> pages =
                serviceRepository.findAllByCompanyId(companyId,pageable);

        return new ApiResponse("success ",true,pages);

    }

    public ApiResponse getByServiceCategory(Integer serviceCategoryId, int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<uz.developer.communication_system.entity.Service> pages =
                serviceRepository.findAllByServiceCategoryId(serviceCategoryId,pageable);

        return new ApiResponse("success ",true,pages);

    }
}
