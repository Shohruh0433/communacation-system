package uz.developer.communication_system.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SimCardDto {

    @NotNull(message = "code null")
    @Size(max = 6,min = 6,message = "code kiritishda xato")
    private String code;
    @NotNull(message = "nomer null")
    @Size(max = 7,min = 7,message = "nomer 7 xonali bo'lsihi kerak")
    private String nomer;
}
