package org.example.questions;

public class SQLQuestion extends AbstractQuestion {

    public SQLQuestion() {
        super("Сколько в реляционных (SQL) базах данных существует типов связей можду таблицами?");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals("3");
    }
}
