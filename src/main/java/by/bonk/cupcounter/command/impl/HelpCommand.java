package by.bonk.cupcounter.command.impl;

import by.bonk.cupcounter.command.CommandHandler;
import by.bonk.cupcounter.enumeration.Role;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Set;

public class HelpCommand implements CommandHandler {
    @Override
    public String getCommand() {
        return "/help";
    }

    @Override
    public SendMessage handleCommand(String command, Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("""
                        /start the command only for new users,
                        /help command info about command,
                        /echo command info about user.
                        """);
        return message;
    }

    @Override
    public Set<Role> getAvailableRoles() {
        return Set.of();
    }
}
