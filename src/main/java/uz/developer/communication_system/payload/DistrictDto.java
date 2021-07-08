package uz.developer.communication_system.payload;

import lombok.Data;
import uz.developer.communication_system.entity.Region;

import javax.persistence.ManyToOne;

@Data
public class DistrictDto {

    private String name;

    private Integer regionId;

}
