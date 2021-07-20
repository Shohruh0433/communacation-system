package uz.developer.communication_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Employee;

import javax.validation.constraints.Email;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    boolean existsByEmail(@Email String email);
}
