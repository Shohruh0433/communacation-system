package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.developer.communication_system.service.ServiceService;

@RestController
@RequestMapping("/api/Service")
public class ServiceController {

    @Autowired
    ServiceService serviceService;



}
