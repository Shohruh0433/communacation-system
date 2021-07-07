package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tariff {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private boolean legan;

    @ManyToOne
    private Company company;

    private double transitionPrice;

    private boolean active;

    private double priceOfMonth;
    private double priceOfDay;

    private double netLimitAll;

    private double netLimitForTelegram;

    private double netLimitForYoutube;

    private double netLimitForInstagram;

    private int sms;

    private Integer minutInNet;
    private Integer minutOutNet;
    private Integer expireDay;

    private double priceofMinInNet;

    private double priceofMinOutNet;

    private double priceofNetAll;

}
