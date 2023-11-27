package com.example.myresale.configuration;

import com.example.myresale.telegramBot.MyResaleNotificationBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfiguration {
    @Value("${telegram.bot.name}")
    private String botToken;
    @Value("${telegram.bot.token}")
    private String botName;

    // Telegram bot API initialize
    @Bean
    public MyResaleNotificationBot initTelegramBot(){
        MyResaleNotificationBot bot = new MyResaleNotificationBot(botName, botToken);
        try{
            var botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException ex){
            ex.printStackTrace();
        }

        return bot;
    }
}
