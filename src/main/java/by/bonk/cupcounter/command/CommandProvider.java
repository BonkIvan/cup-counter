package by.bonk.cupcounter.command;

import by.bonk.cupcounter.command.impl.EchoCommand;
import by.bonk.cupcounter.command.impl.HelpCommand;
import by.bonk.cupcounter.command.impl.StartCommand;
import by.bonk.cupcounter.enumeration.Role;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Data
public class CommandProvider {
    private final HashMap<CommandKey, CommandHandler> commands = new HashMap<>();


    //private static final Command DEFAULT_COMMAND = new NotFoundCommand();


    public CommandProvider() {
        // создаём команды и кладём их в hashmap

        CommandKey startUnknownOrdersKey = new CommandKey("/start", Role.UNKNOWN_ROLE);
        commands.put(startUnknownOrdersKey, new StartCommand());

        CommandKey startOwnerOrdersKey = new CommandKey("/start", Role.ROLE_OWNER);
        commands.put(startOwnerOrdersKey, new StartCommand());

        CommandKey helpUserOrdersKey = new CommandKey("/help", Role.ROLE_USER);
        commands.put(helpUserOrdersKey, new HelpCommand());

        CommandKey helpOwnerOrdersKey = new CommandKey("/help", Role.ROLE_OWNER);
        commands.put(helpOwnerOrdersKey, new HelpCommand());

        // другие команды
        CommandKey userEchoOrdersKey = new CommandKey("/echo", Role.ROLE_USER);
        commands.put(userEchoOrdersKey, new EchoCommand());

        CommandKey ownerEchoOrdersKey = new CommandKey("/echo", Role.ROLE_OWNER);
        commands.put(ownerEchoOrdersKey, new EchoCommand());

    }


    public CommandHandler getCommand(String command, Role role) {
        // ADMIN or USER, orders, GET
        CommandKey key = new CommandKey(command, role);
        CommandHandler command1 = commands.get(key);

        if (command != null) return command1;

        return null;
    }

}
