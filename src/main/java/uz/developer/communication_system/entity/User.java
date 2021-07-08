package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employe {
    @Id
    @GeneratedValue
    private UUID id;
    private String passpostSeria;
    private String passportNumber;
    @Column(unique = true)
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean active;
}
