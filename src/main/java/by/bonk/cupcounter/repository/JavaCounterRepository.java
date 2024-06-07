package by.bonk.cupcounter.repository;

import by.bonk.cupcounter.entity.JavaCounter;

import org.springframework.data.jpa.repository.JpaRepository;



public interface JavaCounterRepository extends JpaRepository<JavaCounter, Long> {

    //ищет пользователя в таблице javaCounter по chatId
    //boolean findJavaCounterByUserChatId(Long userChatId);
    //Проверяет сущетствует ли запись в таблице по chatId
    <S extends JavaCounter> boolean existsJavaCounterByUserChatId(Long userChatId);

    boolean existsJavaCounterByUserChatIdAndDate(Long userChatId, String date);
   JavaCounter findJavaCounterByUserChatId(Long userChatId);

    JavaCounter getReferenceByUserChatId(Long chatId);
}
