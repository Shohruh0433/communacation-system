package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.District;

public interface DistrictRepository extends JpaRepository<District,Integer> {

    boolean existsByName(String name);

    Page<District> findAllByRegion_Id(Integer region_id, Pageable pageable);
}
