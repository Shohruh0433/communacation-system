package uz.developer.communication_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.AutoPopulatingList;
import uz.developer.communication_system.entity.PacketTraffic;
import uz.developer.communication_system.entity.enums.PacketType;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.PacketTrafficRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PacketTrafficService {

    private final PacketTrafficRepository packetTrafficRepository;
    public PacketTrafficService(PacketTrafficRepository packetTrafficRepository) {
        this.packetTrafficRepository = packetTrafficRepository;
    }

    public ApiResponse getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<PacketTraffic> pages = packetTrafficRepository.findAll(pageable);
        return new ApiResponse("natija",true,pages);
    }
    public ApiResponse getPacket(Integer id) {
        Optional<PacketTraffic> optionalPacketTraffic = packetTrafficRepository.findById(id);
        return optionalPacketTraffic.map(packetTraffic -> new ApiResponse(
      "succes", true, packetTraffic)).orElseGet(() -> new ApiResponse("not found", false));
    }

    public ApiResponse getByType(PacketType packetType, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<PacketTraffic> pages = packetTrafficRepository.findAllByPacketType(packetType,pageable);
        return new ApiResponse("natija",true,pages);
    }
}
