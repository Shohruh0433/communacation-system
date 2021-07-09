package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private ServiceCategory serviceCategory;

    private String description;
    private String serviceCode;
    private double priceOfMonth;
    private double priceOfDay;
    private boolean active = true;

    @ManyToOne
    private Company company;
}

