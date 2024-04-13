package by.bonk.cupcounter.repository;

import by.bonk.cupcounter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
