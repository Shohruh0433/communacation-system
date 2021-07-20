package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.SimCard;

import java.util.List;
import java.util.Optional;

public interface SimCardRepository extends JpaRepository<SimCard ,Long> {

       List<SimCard> findAllByCodeOrNumberContainsAndUserNull(String code, String number);


      Optional< SimCard> findByCodeAndNumberAndUserNull(String code, String number);


    List<SimCard> findAllByUserIsNullAndCompany_Id(Integer company_id);

    List<SimCard> findAllByUser_PassportSeriyaAndUser_PassportNumber(String user_passportSeriya, String user_passportNumber);


    boolean existsByCodeAndNumber(String code, String number);


    Optional<SimCard> findBySimCardNumber(String simCardNumber);


 Optional < SimCard> findByCodeAndNumber(String code, String number);


}
