package by.bonk.cupcounter.command;

import by.bonk.cupcounter.entity.User;
import by.bonk.cupcounter.enumeration.Role;
import by.bonk.cupcounter.repository.UserRepository;
import by.bonk.cupcounter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Set;
@Component
@RequiredArgsConstructor
public class EchoCommand implements CommandHandler {

   // private final UserService userService;

    @Override
    public String getCommand() {
        return "";
    }

    @Override
    public SendMessage handleCommand(String command, Long chatId) {
        SendMessage message = new SendMessage();

       /* String userInfo = "Info: \n"
                + "User: " + userService.userName(chatId) + "\n"
                + "Data registration: " + userService.registerDate(chatId).toString() + "\n";*/
        message.setChatId(chatId);
        message.setText("you pressed the start button");
        return message;
    }

    @Override
    public Set<Role> getAvailableRoles() {
        return Set.of();
    }
}
