package uz.developer.communication_system.entity;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import uz.developer.communication_system.entity.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Component
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Override
    public String getAuthority() {
        return name.name();
    }

}
