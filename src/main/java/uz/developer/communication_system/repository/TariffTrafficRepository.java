package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.TariffTraffic;

import java.util.Optional;


public interface TariffTrafficRepository extends JpaRepository<TariffTraffic,Long> {

    Optional<TariffTraffic> findBySimCard_CompanyCodeAndSimCard_Number(String companyCode, String number);


    Optional<TariffTraffic> findBySimCardNumber(String simCardNumber);
}
