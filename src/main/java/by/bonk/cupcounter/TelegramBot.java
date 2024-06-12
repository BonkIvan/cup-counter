package by.bonk.cupcounter;

import by.bonk.cupcounter.MessageService.MessageHandler;
import by.bonk.cupcounter.calendar.CalendarUtil;
import by.bonk.cupcounter.command.CommandHandler;
import by.bonk.cupcounter.command.CommandProvider;
import by.bonk.cupcounter.config.BotConfig;
import by.bonk.cupcounter.enumeration.Role;
import by.bonk.cupcounter.service.JavaCounterService;
import by.bonk.cupcounter.service.UserService;
import by.bonk.cupcounter.service.WaitingUserService;
import by.bonk.cupcounter.utils.UserVerifier;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
    private BotConfig botConfig;


    public final CommandsList commandsList;
    private final UserService userService;
    private final UserVerifier userVerifier;
    private final MessageHandler messageHandler;
    private final JavaCounterService javaCounterService;
    private final WaitingUserService waitingUserService;
    private final CalendarUtil calendarUtil;

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

            //Добавление владельца бота
            if (botConfig.getOwnerId().equals(chatId)) {
                if (!userVerifier.checkUserExists(chatId)) {
                    userService.addUsers(update.getMessage());
                }
            }


            //Проверяет если ли пользователь в базе и не владелец ли бота. после проверки добавляет пользователя в базу waiting_users
            //отправляет (пока только) владельцу бота сообщение с секретным ключем.
            if (messageText.startsWith("/start") && botConfig.getOwnerId() != chatId && userService.checkNewUser(chatId)) {
                waitingUserService.addNewWaitingUser(update.getMessage());
                executeMessage(messageHandler.sendMessageForWaitingUser(message, chatId, "wait you"));
                sendSecretKeyMessageForOwner(chatId, update.getMessage().getChat().getFirstName());
            }


            if (messageText.startsWith("/")) {
                CommandHandler command =  new CommandProvider().getCommand(messageText,userService.getRole(chatId));
                executeMessage(command.handleCommand("привет медвед ", chatId));
            }



            if (waitingUserService.checkWaitingUserByChatId(chatId)
                    && waitingUserService.getSecretKeyForWaitingUserByChatId(chatId).equals(messageText)) {
                executeMessage(waitingUserService.fromWaitingToUser(update.getMessage(), message));
            }


//            if (!userVerifier.checkUserExists(chatId)) {
//                userService.addUsers(update.getMessage());
//            }                                                           //if конец  проверки юзера и дабовление в базу

            // System.out.println(userService.getRole(chatId));


            /**Меню в зависимости от роли пользователя*/
            if (userService.userExistByChatId(chatId)) {
                if (userService.getRole(chatId).equals(Role.ROLE_OWNER)) {
                    executeMessage(messageHandler.sendMessageForOwner(message, chatId, "Hi Owner"));
                } else if (userService.getRole(chatId).equals(Role.ROLE_USER)) {
                    executeMessage(messageHandler.sendMessageForUser(message, chatId, "Hi User"));
                } else if (userService.getRole(chatId).equals(Role.UNKNOWN_ROLE)) {
                    executeMessage(messageHandler.sendMessageForUser(message, chatId, "Hi User"));
                }
            } else if (waitingUserService.checkWaitingUserByChatId(chatId)) {
                executeMessage(messageHandler.sendMessageForWaitingUser(message, chatId, "wait you"));
            }






           /* if (update.getMessage().getReplyMarkup() != null) {

            }*/


        switch (messageText) {
           /* case "/start":
               // message.setText("hi user: " + update.getMessage().getChat().getFirstName() + "!");
                //  message.setText(" id: " + update.getMessage().getChat().getId());
                executeMessage(message);
                break;
            case "/echo":
                message.setText("User: " + update.getMessage().getChat().getFirstName() + " get echo");
                executeMessage(message);
                break;
            case "/help":

                calendarUtil.generateKeyboard(new LocalDate());
                break;*/
            case "Add cup":
                javaCounterService.addCup(chatId);
                message.setText("User: " + update.getMessage().getChat().getFirstName() + " cup has been added");
                executeMessage(message);
            default:
                message.setText("This function not found, " + update.getMessage().getChat().getFirstName());
                executeMessage(message);
        }

        }

    }


    public void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendSecretKeyMessageForOwner(Long chatId, String textToSend) {
        var users = userService.ownerArrayList();
        for (var user : users) {
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(user.getChatId()));
            message.setText("Secret key: \n "
                    + waitingUserService.getSecretKeyForWaitingUserByChatId(chatId)
                    + " \n for user " + textToSend);
            executeMessage(message);
        }


    }

}



