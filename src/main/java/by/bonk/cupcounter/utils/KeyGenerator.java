package by.bonk.cupcounter.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class KeyGenerator {

    private String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String  generateSecretKey(){

        StringBuilder keyBuilder = new StringBuilder(12);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            keyBuilder.append(randomChar);
        }

        return keyBuilder.toString();

    }

    public static boolean compareCodes(String code1, String code2) {
        if (code1.length() != 16 || code2.length() != 16) {
            throw new IllegalArgumentException("Коды должны быть 16-значными");
        }
        return code1.equals(code2);
    }


}
