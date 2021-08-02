package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch,Integer> {


    Page<Branch> findAllByDistrict_Id(Integer district_id, Pageable pageable);
    Page<Branch> findAllByDistrict_Region_Id(Integer district_region_id, Pageable pageable);
}
