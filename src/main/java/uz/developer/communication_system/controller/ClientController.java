package uz.developer.communication_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.service.ClientService;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;


    @GetMapping("/getBalance")
    public HttpEntity<?> getBalance(
//            @RequestParam String phoneNumber
    ){
        SimCard simCard = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ApiResponse apiResponse = clientService.getBalance(simCard);
        return ResponseEntity.ok(apiResponse);

        }






}
