package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.SimCard;

import java.util.List;
import java.util.Optional;

public interface SimCardRepository extends JpaRepository<SimCard ,Long> {

       List<SimCard> findAllByCodeOrNumberContainsAndUserNull(String code, String number);


      Optional< SimCard> findByCodeAndNumberAndUserNull(String code, String number);


}
