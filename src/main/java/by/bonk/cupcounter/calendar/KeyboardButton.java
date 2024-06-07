package by.bonk.cupcounter.calendar;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Model to generate keyboard
 */
@Component
@RequiredArgsConstructor
public class KeyboardButton {



    private String text;

        private String callbackData;

        public KeyboardButton setText(String text) {
            this.text = text;
            return this;
        }

        public String getText() {
            return text;
        }

        public KeyboardButton setCallbackData(String callbackData) {
            this.callbackData = callbackData;
            return this;
        }

        public String getCallbackData() {
            return callbackData;
        }

    }


