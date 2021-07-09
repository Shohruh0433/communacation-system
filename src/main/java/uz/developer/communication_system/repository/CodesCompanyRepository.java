package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.CodesCompany;

import java.util.List;
import java.util.Optional;

public interface CodesCompanyRepository extends JpaRepository <CodesCompany,Integer>{
        boolean existsByCode(String code);

        Optional<CodesCompany> findByCode(String code);




        List<CodesCompany> findAllByCompanyId(Integer company_id);
}
