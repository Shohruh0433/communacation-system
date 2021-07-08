package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.repository.RegionRepository;

@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;
}
