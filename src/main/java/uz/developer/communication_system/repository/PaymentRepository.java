package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.developer.communication_system.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Page<Payment> findAllByStateTrue(Pageable pageable);

    Page<Payment> findAllByStateFalse(Pageable pageable);
}
