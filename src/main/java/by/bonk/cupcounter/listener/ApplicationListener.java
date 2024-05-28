package by.bonk.cupcounter.listener;

import by.bonk.cupcounter.TelegramBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@Component
@RequiredArgsConstructor
//@Log4j2
public class ApplicationListener {

    @Autowired
    TelegramBot bot;

@EventListener(ApplicationReadyEvent.class)
public void onApplicationEvents() {
    try {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    } catch (TelegramApiException e) {
        //   log.error("При инциализации бота произошла ошибка", e);
    }
}
}
