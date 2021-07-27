package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.entity.Region;
import uz.developer.communication_system.entity.TariffTraffic;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.service.TariffTrafficService;

@RestController
@RequestMapping("/api/tariffTraffic")
public class TariffTrafficController {

    @Autowired
    TariffTrafficService tariffTrafficService;


    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = tariffTrafficService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @GetMapping("/getByCodeAndNumber")
    public HttpEntity<?> getByCodeAndNumber(@RequestParam String companyCode,String number) {
        ApiResponse apiResponse = tariffTrafficService.getByCodeAndNumber(companyCode,number);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @GetMapping ("/getAll")
    public HttpEntity<?> getAll(){
    ApiResponse apiResponse = tariffTrafficService.getAll();
    return ResponseEntity.ok(apiResponse);
    }



}
