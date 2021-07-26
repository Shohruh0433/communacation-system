package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.developer.communication_system.entity.enums.PacketType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "packet", uniqueConstraints = { @UniqueConstraint(columnNames = { "packetCode", "company" }) })
//@Table(name = "packet", uniqueConstraints = @UniqueConstraint(name = "company", columnNames = {
//        "packetCode", "company" }))

public class Packet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private PacketType packetType;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String packetCode;
    @Column(nullable = false)
    private String description;

    @NotNull
    @ManyToOne
    private Company company;

    @Column(nullable = false)
    private int expireDay;

    public Long getExpireDayMillis() {
        return expireDay * 1000L * 60 * 60 * 24;
    }
}
