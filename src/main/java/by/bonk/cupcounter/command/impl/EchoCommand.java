package by.bonk.cupcounter.command.impl;

import by.bonk.cupcounter.command.CommandHandler;
import by.bonk.cupcounter.enumeration.Role;
import by.bonk.cupcounter.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Date;
import java.util.Set;

@Component
@Data
public class EchoCommand implements CommandHandler {
    @Autowired
    private UserService userService;

    @Override
    public String getCommand() {
        return "";
    }

    @Override
    public SendMessage handleCommand(String command, Long chatId) {
        SendMessage message = new SendMessage();
        Date date = userService.registerDate(chatId);

        String msg = """
                               User information: 
                                Name: %s
                                Registration date: %s
                                User role: %s
                """
                .formatted(userService.userName(chatId), date.toString(), userService.getRole(chatId).toString());


        message.setChatId(chatId);
        message.setText(msg);
        return message;
    }

    @Override
    public Set<Role> getAvailableRoles() {
        return Set.of();
    }


}
