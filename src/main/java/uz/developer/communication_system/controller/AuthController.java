package uz.developer.communication_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.LoginDto;
import uz.developer.communication_system.payload.RegisterDto;
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

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto){

        ApiResponse register = authService.register(registerDto);
        return ResponseEntity.status(register.isSuccess()?201:409).body(register);
    }

    @GetMapping("/verifyEmail")
    public HttpEntity<?> verify(@RequestParam String emailCode,@RequestParam String email){
        ApiResponse apiResponse = authService.verifyEmail(emailCode, email);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }




}
