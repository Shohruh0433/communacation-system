package uz.developer.communication_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {


}
