package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.repository.DetalizatsionRepository;

@Service
public class DetalizationService {

   private final DetalizatsionRepository detalizatsionRepository;
   @Autowired
    public DetalizationService(DetalizatsionRepository detalizatsionRepository) {
        this.detalizatsionRepository = detalizatsionRepository;
    }


}
