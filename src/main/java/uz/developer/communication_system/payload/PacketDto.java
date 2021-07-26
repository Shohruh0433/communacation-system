package uz.developer.communication_system.payload;

import lombok.Data;
import uz.developer.communication_system.entity.enums.PacketType;

import javax.validation.constraints.NotNull;

@Data
public class PacketDto {

    private PacketType packetType;

    @NotNull(message = "amount is mandatory")
    private int amount;

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
