package org.example.Lab0.Questions;

import java.util.List;

public class MultipleChoice implements Question {
    private final String text;
    private final List<String> options;
    private final int correctIndex;
    private final int points;

    public MultipleChoice(String text, List<String> options, int correctIndex, int points) {
        this.text = text;
        this.options = options;
        this.correctIndex = correctIndex;
        this.points = points;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String display() {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < options.size(); i++) {
            sb.append("\n  ").append(i + 1).append(") ").append(options.get(i));
        }
        return sb.toString();
    }

    @Override
    public boolean check(String answer) {
        return answer.trim().equals(String.valueOf(correctIndex + 1));
    }

    @Override
    public int getPoints() {
        return points;
    }
}
