package uz.developer.communication_system.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CodesCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    @ManyToOne
    private Company company;

}
