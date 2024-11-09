package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;


public class Bot extends TelegramLongPollingBot {
    private HashMap<Long, UserData> users;

    public Bot() {
        users = new HashMap<>();
    }



    @Override

    public String getBotUsername() {
        return "L196_bot";
    }

    @Override
    public String getBotToken() {
        return "8149154900:AAE0tODUHTIg_MhbwSDKM_nTeJEu2hkgy0o";
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        long userId = message.getFrom().getId();
        if (text.equals("/start")) {
            sendText(userId, "Привет! Это тест навыков по Java. Итак начнём");
            users.put(userId, new UserData());
            String question = getQuestion(1);
            sendText(userId, question);
        } else if (users.get(userId).getQuestionNumber() >= 4) {
            sendText(userId, "Ваш рейтинг " + users.get(userId).getScore() + " из 4-х");
            sendText(userId, "Тест закончен!");
        } else {
            UserData userData = users.get(userId);
            int questionNumber = userData.getQuestionNumber();
            boolean result = checkAnswer(questionNumber, text);
            int score = userData.getScore();
            userData.setScore(score+(result ? 1 : 0));
            sendText(userId, result ? "Все верно!" : "Не верно!");
            userData.setQuestionNumber(questionNumber + 1);
            String question = getQuestion(userData.getQuestionNumber());
            sendText(userId, question);
            }
        }




    public String getQuestion(int number) {
        if (number == 1) {
            return "Вопрос 1. Сколько в языке программирования Java есть примитивов?";
        } else if (number == 2) {
            return "Вопрос 2. Сколько в реляционных (SQL) базах данных существует типов связей можду таблицами?";
        } else if (number == 3) {
            return "Вопрос 3. С помощью какой команды в системе контроля версий Git можно посмотреть авторов " +
                    "различных строк в одном файле?";
        } else if (number == 4) {
            return "Вопрос 4. Какие методы HTTP-запросов вы знаете?";
        }
        return "";

    }

    public boolean checkAnswer(int number, String answer) {
        answer = answer.toLowerCase();
        if (number == 1) {
            return answer.equals("8");
        }
        if (number == 2) {
            return answer.equals("3");
        }
        if (number == 3) {
            return answer.contains("blame");
        }
        if (number == 4) {
            return answer.contains("get")
                    && answer.contains("post")
                    && answer.contains("put")
                    && answer.contains("patch")
                    && answer.contains("delete");
        }
        return false;
    }
}
