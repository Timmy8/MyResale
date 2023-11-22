package com.example.myresale.repositories;

import com.example.myresale.entities.TelegramBoutUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramBotUserRepository extends JpaRepository<TelegramBoutUser, Long> {
}
