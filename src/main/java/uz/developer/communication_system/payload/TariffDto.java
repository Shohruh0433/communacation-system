package uz.developer.communication_system.payload;


import lombok.Data;
import uz.developer.communication_system.entity.Company;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class TariffDto {

    @NotNull
    private String name;

    private boolean legal ; // yuridik => true , jismoniy => false ,default jismoniy

    @NotNull
    private Integer companyId;

    @NotNull
    private double transitionPrice;

    @NotNull
    private Integer expireDay;

    private boolean active = true;
    private double priceOfMonth;
    private double priceOfDay;
    private double netLimitAll;
    private double netLimitForTelegram;
    private double netLimitForYoutube;
    private double netLimitForInstagram;
    private int sms;
    private Integer minuteInNet;
    private Integer minuteOutNet;
    private double priceOfMinInNet;
    private double priceOfMinOutNet;
    private double priceOfNetAll;
}
