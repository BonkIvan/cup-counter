package by.bonk.cupcounter.MessageService;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageHandler {

    public SendMessage sendMessageForUser(SendMessage sendMessage, long chatId, String textToSend) {
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("Statistics");
        row.add("Add cup");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Main menu");
        row.add("Back");

        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(keyboardMarkup);

        return sendMessage;
    }

    public SendMessage sendMessageForOwner(SendMessage sendMessage, long chatId, String textToSend) {
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("Statistics");
        row.add("Add cup");
        row.add("get all users");
        row.add("Change role");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Main menu");
        row.add("Back");

        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(keyboardMarkup);

        return sendMessage;
    }

    public SendMessage sendMessageForWaitingUser(SendMessage sendMessage, long chatId, String textToSend) {
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("enter key");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Main menu");
        row.add("Back");

        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(keyboardMarkup);

        return sendMessage;
    }

}
