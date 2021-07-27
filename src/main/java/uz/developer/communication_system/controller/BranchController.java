package uz.developer.communication_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.BranchDto;
import uz.developer.communication_system.service.BranchService;

@RestController
@RequestMapping ("/api/company")

public class BranchController {

    @Autowired
    BranchService companyService;



    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody BranchDto companyDto){

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
    public HttpEntity<?> edit(@RequestBody BranchDto companyDto , @PathVariable Integer id ){

        ApiResponse apiResponse = companyService.edit(companyDto,id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);

    }

    @GetMapping("/get/{districtId}")
    public HttpEntity<?> getByDistrict( @PathVariable Integer districtId ,
                     @RequestParam(defaultValue = "0") int page,
                     @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = companyService.getByDistrict(districtId,page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }


    @GetMapping("/get/{regionId}")
    public HttpEntity<?> getByRegionId( @PathVariable Integer regionId ,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = companyService.getByRegionId(regionId,page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }


    @GetMapping("/get/{companyId}")
    public HttpEntity<?> getByParentCompany( @PathVariable Integer companyId ,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = companyService.getByParentCompany(companyId,page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

}
