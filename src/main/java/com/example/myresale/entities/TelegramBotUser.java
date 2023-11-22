package com.example.myresale.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TelegramBotUser {
    @Id
    private Long id;

    private String username;
    private Long chatId;

    @Temporal(TemporalType.TIMESTAMP)
    private final Date joinDate = new Date();

}
