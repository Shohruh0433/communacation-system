package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.entity.Payment;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PreAuthorize(value = "hasAnyRole('ROLL_PAYMENT')")
   @Transactional
   @PostMapping("/pay")
    public HttpEntity<?> pay(@RequestBody Payment payment){
      ApiResponse apiResponse= paymentService.pay(payment);
      return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
  }
    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR','ROLL_MANAGER','ROLL_PAYMENT')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        ApiResponse apiResponse= paymentService.getAll(page,size);
        return ResponseEntity.ok(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR','ROLL_MANAGER','ROLL_PAYMENT')")
    @GetMapping("/getByStateTrue")
    public HttpEntity<?> getByStateTrue(
          @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        ApiResponse apiResponse= paymentService.getByStateTrue(page,size);
        return ResponseEntity.ok(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR','ROLL_MANAGER','ROLL_PAYMENT')")
    @GetMapping("/getByStateFalse")
    public HttpEntity<?> getByStateFalse(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        ApiResponse apiResponse= paymentService.getByStateFalse(page,size);
        return ResponseEntity.ok(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR','ROLL_MANAGER','ROLL_PAYMENT')")
    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Long id){
        ApiResponse apiResponse= paymentService.getById(id);
        return ResponseEntity.ok(apiResponse);
    }

}
