package by.bonk.cupcounter;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public interface Action {
        BotApiMethod handle(Update update);

        BotApiMethod callback(Update update);

}
