package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
