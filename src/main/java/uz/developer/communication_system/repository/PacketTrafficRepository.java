package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.PacketTraffic;
import uz.developer.communication_system.entity.enums.PacketEnum;

import java.util.Optional;

public interface  PacketTrafficRepository extends JpaRepository<PacketTraffic,Integer> {


    Optional<PacketTraffic> findByCompanyCodeAndNumber(String companyCode, String number);

    Optional<PacketTraffic> findByPacket_IdAndSimCard_Id(Integer packet_id, Long simCard_id);

}
