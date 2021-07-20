package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.developer.communication_system.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
