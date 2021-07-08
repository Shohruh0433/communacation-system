package uz.developer.communication_system.payload;

import lombok.Data;
import uz.developer.communication_system.entity.Company;
import uz.developer.communication_system.entity.District;

import javax.persistence.ManyToOne;

@Data
public class CompanyDto {

    private String name;

    private Integer companyId;

    private Integer districtId;

}
