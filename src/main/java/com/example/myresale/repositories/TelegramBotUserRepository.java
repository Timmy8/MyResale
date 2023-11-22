package com.example.myresale.repositories;

import com.example.myresale.entities.TelegramBotUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramBotUserRepository extends JpaRepository<TelegramBotUser, Long> {
}
