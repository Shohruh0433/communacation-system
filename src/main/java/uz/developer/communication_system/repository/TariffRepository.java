package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Tariff;

public interface TariffRepository extends JpaRepository<Tariff,Long> {

boolean existsByName(String name);

    Page<Tariff> findAllByCompanyId(Integer company_id, Pageable pageable);

    Page<Tariff> findAllByActiveIsTrueAndCompany_Id(Integer company_id, Pageable pageable);

    Page<Tariff> findAllByActiveIsFalseAndCompanyId(Integer company_id, Pageable pageable);

    Page<Tariff> findAllByLegalIsFalseAndCompanyId(Integer company_id, Pageable pageable);

    Page<Tariff> findAllByLegalIsTrueAndCompanyId(Integer company_id, Pageable pageable);


}
