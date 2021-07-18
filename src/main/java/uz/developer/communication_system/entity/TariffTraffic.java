package uz.developer.communication_system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TariffTraffic {


    @Id
    @GeneratedValue 
    private Long id;

    private String  number;
    private double netLimitAll;
    private double netLimitTelegram;
    private double netLimitYoutube;
    private int sms;
    private double minuteOutNet;
    private double minuteInNet;
    private int trafficExpireDate;

}
