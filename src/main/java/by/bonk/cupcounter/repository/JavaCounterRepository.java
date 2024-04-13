package by.bonk.cupcounter.repository;

import by.bonk.cupcounter.entity.JavaCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JavaCounterRepository extends JpaRepository<JavaCounter, Long> {

}
