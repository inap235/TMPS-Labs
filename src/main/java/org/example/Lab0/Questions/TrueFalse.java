package org.example.Lab0.Questions;

public class TrueFalse implements Question {
    private final String text;
    private final boolean correct;
    private final int points;

    public TrueFalse(String text, boolean correct, int points) {
        this.text = text;
        this.correct = correct;
        this.points = points;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String display() {
        return text + " \n 1.true \n 2.false";
    }

    @Override
    public boolean check(String answer) {
        String a = answer.trim();
        if (a.equals("1")) return correct;
        else if (a.equals("2")) return !correct;
        return false;
    }

    @Override
    public int getPoints() {
        return points;
    }
}
