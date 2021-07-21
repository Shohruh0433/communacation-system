package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.developer.communication_system.entity.enums.PacketEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PacketTraffic {
    @Id
    @GeneratedValue
    private Integer id;

//    private PacketEnum packetType;
    private int amount;

    @ManyToOne
    private Packet packet;

    private String companyCode;
    private String  number;
    private Date trafficExpireDate;

    @ManyToOne
    private  SimCard simCard;

//    private double netLimitAll;
//    private double netLimitTelegram;
//    private double netLimitYoutube;
//    private int sms;
//    private double minuteOutNet;
//    private double minuteInNet;




}
