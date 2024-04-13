package by.bonk.cupcounter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "waitingUsers")
@Table(name = "waitingUsers")
@Data
public class WaitingUser {

    @Id
    @Column(name = "chat_id", unique = true)
    private  Long chatId;

    @Column(name = "username", length = 64)
    private String username;


    @Column(name = "secret_key", length = 64)
    private String secretKey;

}
