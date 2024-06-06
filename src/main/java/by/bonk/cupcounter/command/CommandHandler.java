package by.bonk.cupcounter.command;

import by.bonk.cupcounter.enumeration.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;


public interface CommandHandler {
 String getCommand();
 void handleCommand(String command);
 Set<Role> getAvailableRoles();


}
