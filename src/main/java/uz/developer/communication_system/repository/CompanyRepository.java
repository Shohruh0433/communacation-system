package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {


    Page<Company> findAllByDistrict_Id(Integer district_id, Pageable pageable);

    Page<Company> findAllByCompanyId(Integer company_id, Pageable pageable);

    Page<Company> findAllByDistrict_Region_Id(Integer district_region_id, Pageable pageable);
}
