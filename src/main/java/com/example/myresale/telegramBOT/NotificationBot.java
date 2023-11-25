package com.example.myresale.telegramBot;

public interface NotificationBot {
    void sendTextByChatId(Long chatId, String message);
    void sendTextToAllUsers(String message);
}
