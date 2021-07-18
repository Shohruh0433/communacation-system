package uz.developer.communication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.communication_system.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {


}
