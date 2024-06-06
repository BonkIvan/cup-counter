package by.bonk.cupcounter.service;

import by.bonk.cupcounter.entity.JavaCounter;
import by.bonk.cupcounter.enumeration.Role;
import by.bonk.cupcounter.repository.JavaCounterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class JavaCounterService {
    private String dateFormat = "yyyy-MM-dd";
    @Autowired
    private JavaCounterRepository javaCounterRepository;

    @Transactional
    public void addCup(Long chatId) {

        JavaCounter javaCounter = new JavaCounter();
        LocalDate localDate = LocalDate.now();
        String data = localDate.toString();

        if (!checkUserChatId(chatId)) {
            javaCounter.setCupCount(1);
            javaCounter.setDate(data);
            javaCounter.setUserChatId(chatId);
            javaCounterRepository.save(javaCounter);

        } else if (checkUserChatId(chatId) && javaCounterRepository.findJavaCounterByUserChatId(chatId).getDate().equals(data)) {
            javaCounter = javaCounterRepository.getReferenceByUserChatId(chatId);
            System.out.println(javaCounter.toString());
            javaCounter.setCupCount(javaCounter.getCupCount() + 1);
            javaCounterRepository.save(javaCounter);
        }

    }

    private boolean checkUserChatId(Long chatId) {
        return javaCounterRepository.existsJavaCounterByUserChatId(chatId);
    }



}
