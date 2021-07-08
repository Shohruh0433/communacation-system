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
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String passportSeriya;
    private String passportNumber;
    @Column(unique = true)
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private boolean legal; //yuridikkk
    private boolean active;
    private Date birthDate;

    @ManyToMany
    private Set<Role> roles;

}
