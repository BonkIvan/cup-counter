package by.bonk.cupcounter.service;

import by.bonk.cupcounter.entity.WaitingUser;
import by.bonk.cupcounter.repository.WaitingUserRepository;
import by.bonk.cupcounter.utils.KeyGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WaitingUserService {

    private final WaitingUserRepository waitingUserRepository;
    private final KeyGenerator keyGenerator;
    private final UserService userService;

    @Transactional
    public void addNewWaitingUser(Message message) {
        WaitingUser waitingUser = new WaitingUser();
        Long chatId = message.getChatId();
        waitingUser.setChatId(chatId);
        waitingUser.setUsername(message.getChat().getFirstName());
        waitingUser.setSecretKey(keyGenerator.generateSecretKey());
        waitingUserRepository.save(waitingUser);
    }


    public String getSecretKeyForWaitingUserByChatId(Long chatId) {
        return waitingUserRepository.findByChatId(chatId)
                .orElseThrow(() -> new NoSuchElementException("Value not found"))
                .getSecretKey();
    }

    public SendMessage fromWaitingToUser(Message message, SendMessage sendMessage) {
        Long chatId = message.getChatId();
        String key = message.getText();
        sendMessage.setChatId(chatId);
        String secretKey = getSecretKeyForWaitingUserByChatId(chatId);
        if (secretKey.equals(key)) {
            userService.addUsers(message);
            sendMessage.setText("Now " + message.getChat().getFirstName() + " are user!");
            return sendMessage;
        }

        sendMessage.setText("This key  " + key + " is wrong! try again!");
        return sendMessage;

    }

    public boolean checkWaitingUserByChatId(Long chatId) {
         return waitingUserRepository.findByChatId(chatId).isPresent();
    }

}
