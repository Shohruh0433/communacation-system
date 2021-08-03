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

    List<SimCard> findAllByBranchIdAndCreatedAt_YearAndCreatedAt_Month(
            Integer branch_id, int createdAt_year, int createdAt_month);
    List<SimCard> findAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_DayBetween(
            Integer branch_id, int createdAt_year, int createdAt_month, int createdAt_day, int createdAt_day2);
    List<SimCard> findAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(
            Integer branch_id, int createdAt_year, int createdAt_month, int createdAt_day);

    List<SimCard> findAllByCreatedAt_YearAndCreatedAt_Month(
            int createdAt_year, int createdAt_month);
    List<SimCard> findAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_DayBetween(
            int createdAt_year, int createdAt_month, int createdAt_day, int createdAt_day2);
    List<SimCard> findAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(
            int createdAt_year, int createdAt_month, int createdAt_day);
}
