package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Tariff;

public interface TariffRepository extends JpaRepository<Tariff,Long> {
}
