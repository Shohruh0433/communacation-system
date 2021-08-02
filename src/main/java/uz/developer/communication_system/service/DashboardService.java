package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.PacketTrafficRepository;
import uz.developer.communication_system.repository.TariffTrafficRepository;

@Service
public class DashboardService {

  private  final TariffTrafficRepository tariffTrafficRepository;
  private final PacketTrafficRepository packetTrafficRepository;
  @Autowired
    public DashboardService(TariffTrafficRepository tariffTrafficRepository, PacketTrafficRepository packetTrafficRepository) {
        this.tariffTrafficRepository = tariffTrafficRepository;
      this.packetTrafficRepository = packetTrafficRepository;
  }



}
