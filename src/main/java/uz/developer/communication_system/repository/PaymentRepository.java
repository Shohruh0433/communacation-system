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

    @Query(value = "SELECT * FROM Payment p join CodesCompany cc WHERE cc.company.id =:id and cc.code = p.companyCode",nativeQuery = true)
    Page<Payment> findAllByCompany_Id(@Param("id") Integer id,Pageable pageable);

    @Query(value = "SELECT * FROM Payment p join CodesCompany cc WHERE cc.company.id =:id  and p.state = true and cc.code = p.companyCode",nativeQuery = true)
    Page<Payment> findAllByStateTrueAndCompany_Id(@Param("id") Integer id,Pageable pageable);

    @Query(value = "SELECT * FROM Payment p join CodesCompany cc WHERE cc.company.id =:id  and p.state = false and cc.code = p.companyCode ",nativeQuery = true)
    Page<Payment> findAllByStateFalseAndCompany_Id(@Param("id") Integer id,Pageable pageable);
}
