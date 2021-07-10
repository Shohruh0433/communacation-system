package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.entity.Tariff;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.TariffDto;
import uz.developer.communication_system.service.TariffService;

@RestController
@RequestMapping("/api/tarif")

public class TarifController {

   @Autowired
   TariffService tariffService ;


    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody TariffDto tariffDto){

        ApiResponse apiResponse = tariffService.add(tariffDto);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = tariffService.getAll(page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Long id ){

        ApiResponse apiResponse = tariffService.getById(id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody TariffDto tariffDto , @PathVariable Long id ){

        ApiResponse apiResponse = tariffService.edit(tariffDto,id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);

    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {

        ApiResponse apiResponse = tariffService.delete(id);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);

    }

    @GetMapping("/get/{companyId}")
    public HttpEntity<?> getByCompany(@PathVariable Integer companyId,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = tariffService.getByCompany(companyId,page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/get/activeForCompany/{companyId}")
    public HttpEntity<?> getByActiveForCompany(@PathVariable Integer companyId ,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = tariffService.getByActiveForCompany(companyId,page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/get/deletedForCompany/{companyId}}")
    public HttpEntity<?> getByDeletedForCompany(@PathVariable Integer companyId ,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = tariffService.getByDeletedForCompany(companyId,page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/get/byLegalForCompany/{companyId}")
    public HttpEntity<?> getByLegalForCompany(@PathVariable Integer companyId ,
                                          @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = tariffService.getByLegalForCompany(companyId,page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/get/byPhysicalForCompany/{companyId}")
    public HttpEntity<?> getByPhysicalForCompany(@PathVariable Integer companyId ,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = tariffService.getByPhysicalForCompany(companyId,page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

}
