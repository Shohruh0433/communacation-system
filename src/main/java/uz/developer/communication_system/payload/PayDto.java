package uz.developer.communication_system.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Data
public class PayDto {

    private String code;
    private String number;
    private String ownerPay;
    private String typePay;
    private double amount;
}
