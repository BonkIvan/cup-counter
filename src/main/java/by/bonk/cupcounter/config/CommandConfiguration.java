package by.bonk.cupcounter.config;

import by.bonk.cupcounter.command.CommandHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandConfiguration {


    public Map<String, CommandHandler> commandHandlers(List<CommandHandler> commandHandlers) {
        return commandHandlers.stream().collect(Collectors.toMap(CommandHandler::getCommand, C -> C));
    }

}
