package uz.developer.communication_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Company;
import uz.developer.communication_system.entity.UssdCode;

import java.util.List;

public interface UssdCodeRepository extends JpaRepository<UssdCode,Integer> {

    boolean existsByCodeAndCompany_Id(String code, Integer company_id);

}
