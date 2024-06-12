package by.bonk.cupcounter.command;

import by.bonk.cupcounter.enumeration.Role;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;
import java.util.Set;


public interface CommandHandler {
 String getCommand();
 SendMessage handleCommand(String command, Long chatId);
 Set<Role> getAvailableRoles();


}
