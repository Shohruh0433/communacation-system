package uz.developer.communication_system.payload;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Data
public class SimCardForSearchDto {

    private String code;

    private String nomer;

}
