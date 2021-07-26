package uz.developer.communication_system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class SimCard implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private boolean block = false;
    private String companyCode;
    private String number;
    private double balance;
    private boolean active = false;
    @Column(nullable = false)
    private String password;
    private String pinCode;
    @Column(unique = true,nullable = false)
    private String simCardNumber;
    @ManyToOne
    private Company company;
    @ManyToOne
    private User user;
    @ManyToOne
    private Tariff tariff;
    @ManyToOne
    private PacketTraffic paketTraffic;
    @ManyToMany
    private Set<Role> roles;
    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;




    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return pinCode;
    }

    @Override
    public String getUsername() {
        return companyCode+number;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
