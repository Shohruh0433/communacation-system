package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String passportSeries;
    private String passportNumber;
    private String firstName;
    private String lastName;
    private boolean legal; //yuridik
    private Date birthDate;


}
