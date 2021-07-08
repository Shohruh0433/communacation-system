package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Company;
import uz.developer.communication_system.entity.Packet;

public interface PacketRepository extends JpaRepository<Packet, Integer> {

    boolean existsByPacketCodeAndCompanyIdNot(String packetCode, Integer company_id);


    Page<Packet> findAllByCompanyId(Integer company_id, Pageable pageable);
}
