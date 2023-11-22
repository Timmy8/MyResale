package com.example.myresale.telegramBOT;

public interface NotificationBot {
    void sendTextByChatId(Long chatId, String message);
    void sendTextToAllUsers(String message);
}
