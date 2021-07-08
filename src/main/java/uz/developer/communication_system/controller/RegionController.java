package uz.developer.communication_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.service.RegionService;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionService regionService;



    @PostMapping("/add")
      public HttpEntity<?> add(@RequestBody Region region){

        ApiResponse apiResponse = regionService.add(region);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){

        ApiResponse apiResponse = regionService.getAll();

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id ){

        ApiResponse apiResponse = regionService.getById(id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody Region region , @PathVariable Integer id ){

        ApiResponse apiResponse = regionService.edit(region,id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);

    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {

        ApiResponse apiResponse = regionService.delete(id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);

    }

}
