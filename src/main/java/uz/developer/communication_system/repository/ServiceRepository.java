package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Service;

public interface ServiceRepository extends JpaRepository <Service,Integer> {


    boolean existsByName(String name);

    Page<Service> findAllByServiceCategoryId(Integer serviceCategory_id, Pageable pageable);


}
