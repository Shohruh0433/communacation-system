package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.PacketTraffic;
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.entity.enums.PacketType;

import java.util.Optional;

public interface  PacketTrafficRepository extends JpaRepository<PacketTraffic,Integer> {


    Optional<PacketTraffic> findByCompanyCodeAndNumber(String companyCode, String number);

    Optional<PacketTraffic> findByPacketTypeAndSimCard(PacketType packetType, SimCard simCard);


}
