package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.PacketTraffic;
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.entity.enums.PacketType;

import java.util.List;
import java.util.Optional;

public interface  PacketTrafficRepository extends JpaRepository<PacketTraffic,Integer> {

    Optional<PacketTraffic> findBySimCard_CompanyCodeAndSimCard_Number(String companyCode, String number);

    Optional<PacketTraffic> findByPacketTypeAndSimCard(PacketType packetType, SimCard simCard);

    Page<PacketTraffic> findAllByPacketType(PacketType packetType, Pageable pageable);

    List<PacketTraffic> findAllByPacketIdAndActiveIsTrue(Integer packet_id);
}
