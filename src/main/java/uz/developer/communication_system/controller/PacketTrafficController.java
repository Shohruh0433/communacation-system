package uz.developer.communication_system.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.entity.Packet;
import uz.developer.communication_system.entity.PacketTraffic;
import uz.developer.communication_system.entity.enums.PacketType;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.service.PacketTrafficService;

import java.util.List;

@RestController
@RequestMapping("/packetTraffic")
public class PacketTrafficController {

    private final PacketTrafficService packetTrafficService;
    public PacketTrafficController(PacketTrafficService packetTrafficService) {
        this.packetTrafficService = packetTrafficService;
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
       ApiResponse apiResponse = packetTrafficService.getAll(page, size);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getPacket(@PathVariable Integer id){
         ApiResponse apiResponse= packetTrafficService.getPacket(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @GetMapping("/getByType")
    public HttpEntity<?> getByType(@RequestParam PacketType packetType,
           @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        ApiResponse apiResponse = packetTrafficService.getByType(packetType,page, size);
        return ResponseEntity.ok(apiResponse);
    }

}
