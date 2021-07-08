package uz.developer.communication_system.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PacketDto {

    @NotNull(message = "type is mandatory")
    private String type;

    @NotNull(message = "amount is mandatory")
    private double amount;

    @NotNull(message = "price is mandatory")
    private double price;

    @NotNull(message = "expireDay is mandatory")
    private int expireDay;

    @NotNull(message = "packetCode is mandatory")
    private String packetCode;

    @NotNull(message = "description is mandatory")
    private String description;

    @NotNull(message = "companyId is mandatory")
    private Integer companyId;

}
