package by.bonk.cupcounter.service;

import by.bonk.cupcounter.entity.JavaCounter;
import by.bonk.cupcounter.enumeration.Role;
import by.bonk.cupcounter.repository.JavaCounterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JavaCounterService {
    @Autowired
    private JavaCounterRepository javaCounterRepository;

    private String dateFormat = "yyyy-MM-dd";

    @Transactional
    public void addCup(Long chatId) {

        JavaCounter javaCounter = new JavaCounter();

        Date date = new Date();
        javaCounter.setCupCount(1);
        javaCounter.setDate(date);
        javaCounter.setUserChatId(chatId);
        javaCounterRepository.save(javaCounter);

    }

    private boolean checkUserChatId(Long chatId) {
        return javaCounterRepository.existsJavaCounterByUserChatId(chatId);
    }


}
