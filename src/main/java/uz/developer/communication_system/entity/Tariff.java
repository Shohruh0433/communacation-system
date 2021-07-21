package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tariff {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean legal ; // legal => true , physical => false ,default physical
    @ManyToOne
    private Company company;
    private double transitionPrice;
    private boolean active = true;
    private double priceOfMonth;
    private double priceOfDay;
    private double netLimitAll;
    private double netLimitForTelegram;
    private double netLimitForYoutube;
    private double netLimitForInstagram;
    private long sms;
    private long minuteInNet;
    private long minuteOutNet;

    private long expireDay;

    private double priceOfMinInNet;
    private double priceOfMinOutNet;
    private double priceOfNetAll;
    private double priceOfSms;



    public Long getExpireDayMillis() {
        return expireDay * 1000L * 60 * 60 * 24;
    }


}
