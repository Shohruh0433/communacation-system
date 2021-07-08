package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "packet", uniqueConstraints = { @UniqueConstraint(columnNames = { "packetCode", "company" }) })
public class Packet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int expireDay;

    @Column(nullable = false)
    private String packetCode;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @ManyToOne
    private Company company;
}
