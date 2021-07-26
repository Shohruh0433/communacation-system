package uz.developer.communication_system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TariffTraffic {


    @Id
    @GeneratedValue 
    private Long id;
    @Column(nullable = false)
    private String  number;
    @Column(nullable = false)
    private String  companyCode;
    @Column(unique = true,nullable = false)
    private String simCardNumber;
    private double netLimitAll;
    private double netLimitTelegram;
    private double netLimitYoutube;
    private long sms;
    private long minuteOutNet;
    private long minuteInNet;
    private Date trafficExpireDate;

}
