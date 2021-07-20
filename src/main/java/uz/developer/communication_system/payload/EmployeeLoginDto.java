package uz.developer.communication_system.payload;

import lombok.Data;
import uz.developer.communication_system.entity.Role;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class EmployeeLoginDto {

    @NotNull
    private String username;

    @NotNull
    private String password;


}
