package org.example.Lab0.Questions;

public class FillInBlank implements Question {
    private final String text;
    private final String correctAnswer;
    private final int points;

    public FillInBlank(String text, String correctAnswer, int points) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String display() {
        return text + " (type the missing word)";
    }

    @Override
    public boolean check(String answer) {
        return answer.trim().equalsIgnoreCase(correctAnswer);
    }

    @Override
    public int getPoints() {
        return points;
    }
}
