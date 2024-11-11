package org.example.questions;

public class GitQuestion extends AbstractQuestion{
    public GitQuestion() {
        super("С помощью какой команды в системе контроля версий Git можно посмотреть авторов различных строк в одном файле?");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.toLowerCase().contains("blame");
    }
}
