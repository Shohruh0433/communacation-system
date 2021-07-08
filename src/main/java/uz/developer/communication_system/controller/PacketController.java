package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.entity.Packet;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.PacketDto;
import uz.developer.communication_system.service.PacketService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/packet")
public class PacketController {

    @Autowired
    private PacketService packetService;

    /**
     * GET ALL PACKET
     * @param page
     * @param size
     * @return List<Packet>
     */
    @GetMapping("/list")
    public ResponseEntity<List<Packet>> getPackets(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<Packet> packets = packetService.getPackets(page, size);

        return ResponseEntity.ok(packets);
    }

    /**
     * GET ONE PACKET
     * @param id
     * @return Packet
     */
    @GetMapping("/{id}")
    public ResponseEntity<Packet> getPacket(@PathVariable Integer id){
        Packet packet = packetService.getPacket(id);
        if (packet == null)
            return ResponseEntity.status(409).body(null);

        return ResponseEntity.status(202).body(packet);
    }

    /**
     * SAVE PACKET
     * @param packetDto
     * @return ApiResponse
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> savePacket(@Valid @RequestBody PacketDto packetDto){
        ApiResponse apiResponse = packetService.savePacket(packetDto);
//        if (apiResponse.isSuccess())
//            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }


    /**
     * EDIT PACKET
     * @param id
     * @param packetDto
     * @return ApiResponse
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> editPacket(@PathVariable Integer id, @Valid @RequestBody PacketDto packetDto){
        ApiResponse apiResponse = packetService.editPacket(id, packetDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }


    /**
     * DELETE PACKET
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePacket(@PathVariable Integer id){
        ApiResponse apiResponse = packetService.deletePacket(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
