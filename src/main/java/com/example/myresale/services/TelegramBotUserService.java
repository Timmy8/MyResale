package com.example.myresale.services;

import com.example.myresale.entities.TelegramBoutUser;
import com.example.myresale.repositories.TelegramBotUserRepository;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotUserService {
    private final TelegramBotUserRepository repository;

    public TelegramBotUserService(TelegramBotUserRepository repository) {
        this.repository = repository;
    }

    public void saveTelegramBotUser(TelegramBoutUser botUser){
        repository.save(botUser);
    }

}
