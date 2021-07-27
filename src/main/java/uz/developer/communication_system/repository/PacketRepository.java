package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Packet;

public interface PacketRepository extends JpaRepository<Packet, Integer> {

    boolean existsByPacketCode(String packetCode);

}
