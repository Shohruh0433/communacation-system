package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Service;

public interface ServiceRepository extends JpaRepository <Service,Integer> {


}
