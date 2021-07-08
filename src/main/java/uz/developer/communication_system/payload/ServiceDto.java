package uz.developer.communication_system.payload;

import lombok.Data;
import uz.developer.communication_system.entity.ServiceCategory;

import javax.persistence.ManyToOne;

@Data
public class ServiceDto {

    private String name;
    private Integer serviceCategoryId;
    private String description;
    private String serviceCode;
    private double priceOfMonth;
    private double priceOfDay;
}
