package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.entity.UssdCode;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.service.UssdCodeService;

@RestController
@RequestMapping("/api/ussdCode")
public class UssdCodeController {
    @Autowired
    UssdCodeService ussdCodeService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody UssdCode ussdCode){
        ApiResponse apiResponse = ussdCodeService.add(ussdCode);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }
    @GetMapping("/getAll")
    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        ApiResponse apiResponse = ussdCodeService.getAll(page,size);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }
    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id ){
        ApiResponse apiResponse = ussdCodeService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody UssdCode ussdCode , @PathVariable Integer id ){
        ApiResponse apiResponse = ussdCodeService.edit(ussdCode,id);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

}
