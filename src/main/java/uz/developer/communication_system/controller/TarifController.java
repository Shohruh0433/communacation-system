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

    @GetMapping("/get/active")
    public HttpEntity<?> getByActive( @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = tariffService.getByActive(page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/get/deleted")
    public HttpEntity<?> getByDeleted(  @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = tariffService.getByDeleted(page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/get/byLegal")
    public HttpEntity<?> getByLegal( @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = tariffService.getByLegal(page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @GetMapping("/get/byPhysical")
    public HttpEntity<?> getByPhysical(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size){

        ApiResponse apiResponse = tariffService.getByPhysical(page,size);

        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

}
