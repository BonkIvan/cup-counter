package by.bonk.cupcounter.calendar;

import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.List;

/*@Component
@RequiredArgsConstructor*/
/*public class ButtonCalendar {

        private static final InlineKeyboardButton START_BUTTON = new InlineKeyboardButton("Start");
        private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton("Help");
        private final CalendarUtil calendarUtil;
        String data = calendarUtil.generateKeyboard(new LocalDate());
        private static final InlineKeyboardButton CALENDAR = new InlineKeyboardButton();

        public static InlineKeyboardMarkup inlineMarkup() {
            START_BUTTON.setCallbackData("/start");
            HELP_BUTTON.setCallbackData("/help");



            List<InlineKeyboardButton> rowInline = List.of(START_BUTTON, HELP_BUTTON);
            List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);

            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            markupInline.setKeyboard(rowsInLine);

            return markupInline;
        }
    }*/

