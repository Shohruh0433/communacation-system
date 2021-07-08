package uz.developer.communication_system.payload;

import lombok.Data;
import uz.developer.communication_system.entity.Company;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class UssdCodeDto {

    private String code;

    private String description;

    private Integer companyId;

}
