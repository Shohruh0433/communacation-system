package uz.developer.communication_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.SimCardDto;
import uz.developer.communication_system.payload.SimCardForOrderDto;
import uz.developer.communication_system.payload.SimCardForSearchDto;
import uz.developer.communication_system.service.SimCardService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/simCard")
public class SimCardController {


    @Autowired
    SimCardService simCardService;

    @PostMapping("/add")
    public ApiResponse addnew(@Valid @RequestBody SimCardDto simCardDto){
        return simCardService.add(simCardDto);
    }
    //sim kartaga buyurtma ya'ni simm carta sotib olish
    @PostMapping("/order")
    public ApiResponse order(@Valid @RequestBody SimCardForOrderDto simCardForOrderDto){
        return simCardService.simCardOrder(simCardForOrderDto);
    }
    //nomer orqali bo'sh bo'lgan sim kartani qidirish
    @GetMapping("/get/search")
    public ApiResponse search(@RequestBody SimCardForSearchDto simCardForSearchDto){
        return simCardService.getSearchSimCard(simCardForSearchDto);
    }

    @PostMapping("/block")
    public ApiResponse block(@RequestBody SimCardForSearchDto simCardForSearchDto){
        return  simCardService.blockSimCard(simCardForSearchDto);
    }

    //shu kompaniyaga tegishli barcha egasi yo'q sim kartalarni chiqarish
    @GetMapping("/get/{id}")
    public ApiResponse getSimCards(@PathVariable int id){
        return simCardService.getAllUserNullSimcards(id);
    }

    //shu userga tegishli sim card larni ko'rish
    @GetMapping("/getbyPassport")
    public ApiResponse getSimCardByPassport(@RequestParam String  seria,@RequestParam String number){
        return simCardService.getAllByUserPassport(seria,number);
    }




}
