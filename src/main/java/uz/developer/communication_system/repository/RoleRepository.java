package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.developer.communication_system.entity.Role;
import uz.developer.communication_system.entity.enums.RoleEnum;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findAllByName(RoleEnum name);
}
