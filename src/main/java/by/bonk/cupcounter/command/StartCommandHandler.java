package by.bonk.cupcounter.command;

import by.bonk.cupcounter.enumeration.Role;

import java.util.Set;

public class StartCommandHandler implements CommandHandler {
    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public void handleCommand(String command) {
    //Обработка
    }

    @Override
    public Set<Role> getAvailableRoles() {
        return Set.of(Role.ROLE_ADMIN, Role.ROLE_USER);
    }
}
