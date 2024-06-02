package by.bonk.cupcounter.service;

import by.bonk.cupcounter.config.BotConfig;
import by.bonk.cupcounter.entity.User;
import by.bonk.cupcounter.enumeration.Role;
import by.bonk.cupcounter.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Timestamp;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BotConfig botConfig;


    @Transactional
    public void addUsers(Message message){

        final User user = new User();
        if(userRepository.findById(message.getChatId()).isEmpty()){
            var chatId = message.getChatId();
            var chat = message.getChat();
            var ownerId = botConfig.getOwnerId();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            if (checkOwner(chatId)) {
                user.setRole(Role.ROLE_OWNER);
            } else {
                user.setRole(Role.ROLE_USER);
            }
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);
           // log.info("User saved: "+user.toString());
        }


    }

    private boolean checkOwner(Long chatId){
        return Objects.equals(botConfig.getOwnerId(), chatId);
    }

}
