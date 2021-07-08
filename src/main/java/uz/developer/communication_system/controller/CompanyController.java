package uz.developer.communication_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.entity.Company;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.CompanyDto;
import uz.developer.communication_system.service.CompanyService;

@RestController
@RequestMapping ("company")

public class CompanyController {

    @Autowired
    CompanyService companyService;



    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody CompanyDto companyDto){

        ApiResponse apiResponse = companyService.add(companyDto);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = companyService.getAll(page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id ){

        ApiResponse apiResponse = companyService.getById(id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody CompanyDto companyDto , @PathVariable Integer id ){

        ApiResponse apiResponse = companyService.edit(companyDto,id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);

    }



}
