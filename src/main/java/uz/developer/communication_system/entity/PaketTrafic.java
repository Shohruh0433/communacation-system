package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaketTrafic {
    @Id
    @GeneratedValue
    private Integer id;
    private String code;
    private String  number;
    private double netLimitAll;
    private double netLimitTelegram;
    private double netLimitYoutube;
    private int sms;
    private double minutOutNet;
    private double minutInNet;

    private int traficExpireDate;
}
