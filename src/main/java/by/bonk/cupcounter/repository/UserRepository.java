package by.bonk.cupcounter.repository;

import by.bonk.cupcounter.entity.User;
import by.bonk.cupcounter.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
 //   Optional<Role>  findRoleByChatId(Long chatId);
    User getReferenceByChatId(Long chatId);

    Optional<Role> getRoleByChatId(long chatId);

}
