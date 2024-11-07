package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class Bot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "L196_bot";
    }

    @Override
    public String getBotToken() {
        return "8149154900:AAE0tODUHTIg_MhbwSDKM_nTeJEu2hkgy0o";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
    }
}