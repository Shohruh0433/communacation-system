package uz.developer.communication_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.service.RegionService;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionService regionService;



    @PostMapping("/add")
      public HttpEntity<?> add(){

    }




}
