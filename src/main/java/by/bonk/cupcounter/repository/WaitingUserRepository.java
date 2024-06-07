package by.bonk.cupcounter.repository;

import by.bonk.cupcounter.entity.WaitingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
//@SuppressWarnings("ALL")
public interface WaitingUserRepository extends JpaRepository<WaitingUser, Long> {

    Optional<WaitingUser> findByChatId(Long chatId);

    Optional<String> getSecretKeyByChatId(Long chatId);



}
