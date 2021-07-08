package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.entity.ServiceCategory;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.service.ServiceCategoryService;

@RestController
@RequestMapping("/api/serviceCategory")
public class ServiceCategoryController {

    @Autowired
    ServiceCategoryService serviceCategoryService;


    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody ServiceCategory serviceCategory){

        ApiResponse apiResponse = serviceCategoryService.add(serviceCategory);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = serviceCategoryService.getAll(page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id ){

        ApiResponse apiResponse = serviceCategoryService.getById(id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody ServiceCategory serviceCategory , @PathVariable Integer id ){

        ApiResponse apiResponse = serviceCategoryService.edit(serviceCategory,id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);

    }


}
