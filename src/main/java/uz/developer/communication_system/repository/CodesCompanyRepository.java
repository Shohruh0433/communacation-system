package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.CodesCompany;

public interface CodesCompanyRepository extends JpaRepository <CodesCompany,Integer>{
        boolean existsByCode(String code);
        CodesCompany findByCode(String code);
}
