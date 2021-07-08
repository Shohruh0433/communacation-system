package uz.developer.communication_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.ServiceCategory;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory,Integer> {

    boolean existsByName(String name);
}
