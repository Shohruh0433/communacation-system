package uz.developer.communication_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.developer.communication_system.entity.Company;
import uz.developer.communication_system.service.CompanyService;

@RestController
@RequestMapping ("company")

public class CompanyController {

    @Autowired
    CompanyService companyService;


}
