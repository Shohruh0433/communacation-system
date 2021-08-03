package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import uz.developer.communication_system.service.DetalizationService;

public class DetalizatsionController {

    private final DetalizationService detalizationService;
    @Autowired
    public DetalizatsionController(DetalizationService detalizationService) {
        this.detalizationService = detalizationService;
    }

}
