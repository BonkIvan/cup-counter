package by.bonk.cupcounter;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class CommandsList {

   private List<String> commands = new ArrayList<>();

    private CommandsList(List<String> commands) {
        this.commands = commands;
        commands.add("/start");
        commands.add("/help");
        commands.add("/statistics");
        commands.add("/info");
        commands.add("/stop");
    }


    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
