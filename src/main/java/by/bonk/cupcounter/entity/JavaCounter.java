package by.bonk.cupcounter.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity(name = "java_counter")
@Table(name = "java_counter")
@Data
public class JavaCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userChatId", nullable = false)
    private  Long userChatId;

  /*  @Column(name = "date", nullable = false)
    private String date;*/

  @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "cupCount")
    private int cupCount;

   /* @ManyToOne(optional = false)
    @JoinColumn(name = "chat_id", nullable = false)
    private User user;*/


}
