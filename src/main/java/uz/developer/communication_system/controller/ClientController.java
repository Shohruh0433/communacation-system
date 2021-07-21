package uz.developer.communication_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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

        @PutMapping("/changeTariff/{tariffId}")
    public HttpEntity<?> changeTariff(@PathVariable Long tariffId){
            SimCard simCard = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         ApiResponse apiResponse = clientService.changeTariff(tariffId,simCard);
            return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
        }

    @GetMapping
    public HttpEntity<?> getMyTariffTraffic(){

        SimCard simCard = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApiResponse apiResponse= clientService.getMyTariffTraffic(simCard);
        return ResponseEntity.ok(apiResponse);
    }





}
