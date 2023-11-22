package com.example.myresale;

import com.example.myresale.telegramBOT.MyResaleNotificationBot;
import com.mysql.cj.log.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.logging.Logger;

@SpringBootApplication
public class MyResaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyResaleApplication.class, args);
    }
}
