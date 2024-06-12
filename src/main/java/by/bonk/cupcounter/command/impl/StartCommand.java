package by.bonk.cupcounter.command.impl;

import by.bonk.cupcounter.command.CommandHandler;
import by.bonk.cupcounter.enumeration.Role;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Set;

public class StartCommand implements CommandHandler {
    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public SendMessage handleCommand(String command, Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("you pressed the start button");
        return message;
    }

    @Override
    public Set<Role> getAvailableRoles() {
        return Set.of(Role.ROLE_ADMIN, Role.ROLE_USER, Role.ROLE_OWNER);
    }
}
