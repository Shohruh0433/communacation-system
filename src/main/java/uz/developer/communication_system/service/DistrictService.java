package uz.developer.communication_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.District;
import uz.developer.communication_system.repository.DistrictRepository;

@Service
public class DistrictService {


    @Autowired
    DistrictRepository districtRepository;


}
