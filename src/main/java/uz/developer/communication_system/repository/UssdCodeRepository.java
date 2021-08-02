package uz.developer.communication_system.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.UssdCode;

public interface UssdCodeRepository extends JpaRepository<UssdCode,Integer> {

    boolean existsByCode(String code);
}
