package uz.developer.communication_system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class SimCard {
    @Id
    @GeneratedValue
    private Long id;
    private boolean block;
    private String code;
       private String number;
    private double balance;
    private boolean active;
    @ManyToOne
    private Company company;
    @ManyToOne
    private User user;

    @OneToOne
    private Tariff tariff;

    @ManyToOne
    private PaketTraffic paketTraffic;

}
