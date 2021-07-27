package uz.developer.communication_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.SimCard;

import java.util.List;
import java.util.Optional;

public interface SimCardRepository extends JpaRepository<SimCard ,Long> {


      Optional< SimCard> findByCompanyCodeAndNumberAndUserNull(String code, String number);


    List<SimCard> findAllByUserIsNull();

    List<SimCard> findAllByUser_PassportSeriesAndUser_PassportNumber(String user_passportSeries, String user_passportNumber);


    Optional<SimCard> findBySimCardNumber(String simCardNumber);


    Optional <SimCard> findByCompanyCodeAndNumber(String code, String number);

    List<SimCard> findAllByNumberContainsAndUserNull(String number);

    List<SimCard> findAllByNumberContainsAndUserNullAndCompanyCode(String number, String companyCode);
}
