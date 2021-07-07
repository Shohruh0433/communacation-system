package uz.developer.communication_system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TariffTrafic {
    @Id
    @GeneratedValue
    private Long id;
    private String  number;
    private double netLimitAll;
    private double netLimitTelegram;
    private double netLimitYoutube;
    private int sms;
    private double minutOutNet;
    private double minutInNet;

    private int traficExpireDate;

}
