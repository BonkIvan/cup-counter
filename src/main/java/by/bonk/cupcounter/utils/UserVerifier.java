package by.bonk.cupcounter.utils;

import by.bonk.cupcounter.repository.JavaCounterRepository;
import by.bonk.cupcounter.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserVerifier {
    private final UserRepository userRepository;

    public boolean checkUserExists(Long chatId){

        return userRepository.existsById(chatId);
    }


}
