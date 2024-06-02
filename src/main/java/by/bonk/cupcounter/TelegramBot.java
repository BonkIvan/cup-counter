package by.bonk.cupcounter;

import by.bonk.cupcounter.config.BotConfig;
import by.bonk.cupcounter.service.JavaCounterService;
import by.bonk.cupcounter.service.UserService;
import by.bonk.cupcounter.utils.UserVerifier;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


@Component
@Data
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
    private  BotConfig botConfig;

    @Autowired
    public CommandsList commandsList;

    @Autowired
    private UserService userService;
    @Autowired
    private UserVerifier userVerifier;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private JavaCounterService javaCounterService;


    //  private EchoAction echoAction = new EchoAction("/echo");




    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

                String messageText = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
            if (!userVerifier.checkUserExists(chatId)) {
                userService.addUsers(update.getMessage());
             }                                                           //if конец  проверки юзера и дабовление в базу



                switch (messageText) {
                    case "/start":
                        message.setText("hi user: " + update.getMessage().getChat().getFirstName() + "!");
                      //  message.setText(" id: " + update.getMessage().getChat().getId());
                        executeMessage(message);
                        break;
                    case "/echo":
                        message.setText("User: " + update.getMessage().getChat().getFirstName() + " get echo");
                        executeMessage(message);
                        break;
                    case "/stop":
                        Action echoAction = new EchoAction("update");
                        echoAction.callback(update).getMethod();
                        break;
                    case "Add cup":
                        javaCounterService.addCup(chatId);
                        message.setText("User: " + update.getMessage().getChat().getFirstName() + " cup has been added");
                        executeMessage(message);
                    default:
                        message.setText("This function not found, " + update.getMessage().getChat().getFirstName());
                        executeMessage(message);
                }


          /*  message.setChatId(chatId);
            message.setText("hi user: " + update.getMessage().getChat().getFirstName()+"!");
            executeMessage(message);*/
            }

    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
      //  row.add("Statistics");
        row.add("Add cup");
        row.add("Price per cup");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Main menu");
        row.add("Back");

        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        executeMessage(message);
    }



    public void executeMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

}
