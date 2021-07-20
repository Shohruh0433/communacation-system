package uz.developer.communication_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.EmployeeLoginDto;
import uz.developer.communication_system.payload.EmployeeRegisterDto;
import uz.developer.communication_system.payload.SimCardDto;
import uz.developer.communication_system.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping("/registerSimCard")
//    public ResponseEntity<?> registerSimCard(@Valid @RequestBody SimCardDto simCardDto){
//
//        ApiResponse register = authService.registerSimCard(simCardDto);
//        return ResponseEntity.status(register.isSuccess()?201:409).body(register);
//    }


    @PostMapping("/loginSimCard")
    public ResponseEntity<?> loginSimCard(@Valid @RequestBody SimCardDto simCardDto){
        return ResponseEntity.ok(authService.loginSimCard(simCardDto));
    }


    @PostMapping("/loginEmployee")
    public ResponseEntity<?> loginEmployee(@Valid @RequestBody EmployeeLoginDto employeeDto){
        return ResponseEntity.ok(authService.loginEmployee(employeeDto));
    }

        @PostMapping("/registerEmployee")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody EmployeeRegisterDto employeeRegisterDto){

        ApiResponse apiResponse = authService.registerEmployee(employeeRegisterDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }


}
