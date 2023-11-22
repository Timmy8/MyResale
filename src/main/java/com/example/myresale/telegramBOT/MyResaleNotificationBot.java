package com.example.myresale.telegramBOT;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyResaleNotificationBot extends TelegramLongPollingBot {
    private final String botToken;
    private final String botName;

    public MyResaleNotificationBot(String botToken, String botName) {
        this.botToken = botToken;
        this.botName = botName;
    }

    public void sendText(String message){
        SendMessage sendMessage = SendMessage.builder().text(message).build();
        try{
            execute(sendMessage);
        } catch (TelegramApiException ex){
            ex.printStackTrace();
        }

    }

    @Override
    public String getBotUsername(){
        return botName;
    }

    @Override
    public String getBotToken(){
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update){

    }

}
