package by.bonk.cupcounter.service;

import by.bonk.cupcounter.config.BotConfig;
import by.bonk.cupcounter.entity.User;
import by.bonk.cupcounter.enumeration.Role;
import by.bonk.cupcounter.repository.UserRepository;
import by.bonk.cupcounter.repository.WaitingUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.*;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BotConfig botConfig;
    private final WaitingUserRepository waitingUserRepository;


    @Transactional
    public void addUsers(Message message) {

        final User user = new User();
        if (userRepository.findById(message.getChatId()).isEmpty()) {
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

    public boolean checkOwner(Long chatId) {
        return Objects.equals(botConfig.getOwnerId(), chatId);
    }

    public Role getRole(Long chatId) {

        return userRepository.getReferenceByChatId(chatId).getRole();

    }

    public boolean checkNewUser(Long chatId) {
        if (userExistByChatId(chatId) && waitingUserExistByChatId(chatId)) {
            return false;

        }
        return true;
    }

    public boolean userExistByChatId(Long chatId) {
        return userRepository.findById(chatId).isPresent();
    }

    public boolean waitingUserExistByChatId(Long chatId) {
        return waitingUserRepository.findByChatId(chatId).isPresent();
    }

    public ArrayList<User> userArrayList() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public ArrayList<User> ownerArrayList() {
        ArrayList<User> userArrayList = new ArrayList<>();
        for (User user : userArrayList()) {
            if (user.getRole() == Role.ROLE_OWNER) {
                userArrayList.add(user);
            }
        }

        return userArrayList();
    }

    public String userName(Long chatId) {
        return userRepository.getReferenceByChatId(chatId).getUserName();
    }

    public Date registerDate(Long chatId) {
        return userRepository.getReferenceByChatId(chatId).getRegisteredAt();
    }

}
