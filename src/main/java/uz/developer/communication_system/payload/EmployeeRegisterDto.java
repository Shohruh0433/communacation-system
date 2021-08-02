package uz.developer.communication_system.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
@Data
public class EmployeeRegisterDto {

    @NotNull
    @Size(min = 2,max = 2)
    private String passportSeries;

    @NotNull
    @Size(min = 7,max = 7)
    private String passportNumber;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Set<Integer> roles;

}
