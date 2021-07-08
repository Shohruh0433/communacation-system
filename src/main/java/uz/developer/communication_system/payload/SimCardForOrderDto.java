package uz.developer.communication_system.payload;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SimCardForOrderDto {
    @Size(min = 6,max = 6)
    @NotNull
    private String code;
    @Size(min = 7,max = 7)
    @NotNull
    private String  nomer;

    @NotNull
    @Size(min = 2,max = 2)
    private String passportSeria;


    @NotNull
    @Size(min = 7,max = 7)
    private String passportNumber;

    @NotNull
    private Long tariff_id;


}
