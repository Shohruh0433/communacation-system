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

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody TariffTraffic tariffTraffic) {

        ApiResponse apiResponse = tariffTrafficService.add(tariffTraffic);

        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        ApiResponse apiResponse = tariffTrafficService.getAll(page, size);

        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {

        ApiResponse apiResponse = tariffTrafficService.getById(id);

        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody TariffTraffic tariffTraffic, @PathVariable Long id) {

        ApiResponse apiResponse = tariffTrafficService.edit(tariffTraffic, id);

        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }


}
