package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.repository.ServiceRepository;

@Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;


}
