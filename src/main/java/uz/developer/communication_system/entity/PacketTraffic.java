package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.developer.communication_system.entity.enums.PacketType;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PacketTraffic {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private PacketType packetType;
    private Date trafficExpireDate;
    private int amount;
    @ManyToOne
    private  SimCard simCard;
    @ManyToOne
    private Packet packet;

}
