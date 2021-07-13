package uz.developer.communication_system.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @NotNull
    @Size(min = 6,max = 6)
     private String phoneCode;
    @NotNull
    @Size(min = 7,max = 7)
    private String phoneNumber;
    @NotNull
    @Size(min = 2,max = 10)
    private String password;
}
