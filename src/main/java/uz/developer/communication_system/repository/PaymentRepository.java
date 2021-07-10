package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
