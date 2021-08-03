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

    @OneToOne
    private SimCard simCard;
    private double netLimitAll;
    private double netLimitTelegram;
    private double netLimitYoutube;
    private long sms;
    private long minuteOutNet;
    private long minuteInNet;
    private Date trafficExpireDate;
    private boolean active = true;
    @ManyToOne
    private Tariff tariff;

}
