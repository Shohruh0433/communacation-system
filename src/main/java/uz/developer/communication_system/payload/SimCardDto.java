package uz.developer.communication_system.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import uz.developer.communication_system.entity.Tariff;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
public class SimCardDto {

    @NotNull(message = "code null")
    @Size(max = 6,min = 6,message = "code kiritishda xato")
    private String code;
    @NotNull(message = "number null")
    @Size(max = 7,min = 7,message = "number 7 xonali bo'lsihi kerak")
    private String number;

    private  Long userId;

    private List<Integer> roles;

    private String password;

    public String getFullNumber() {
        return code+number;
    }


}
