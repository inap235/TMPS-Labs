package org.example.Lab1.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class MultipleChoice implements Question {
    private final String text;
    private final List<String> options;
    private int correctIndex;
    private final int points;

    // Builder: objects are created only through MultipleChoice.Builder
    private MultipleChoice(Builder builder) {
        this.text = builder.text;
        this.options = new ArrayList<>(builder.options);
        this.correctIndex = builder.correctIndex;
        this.points = builder.points;
    }

    // Prototype: deep copy, the copy gets its own options list
    private MultipleChoice(MultipleChoice other) {
        this.text = other.text;
        this.options = new ArrayList<>(other.options);
        this.correctIndex = other.correctIndex;
        this.points = other.points;
    }

    @Override
    public Question copy() {
        return new MultipleChoice(this);
    }

    @Override
    public void shuffleOptions() {
        String correctOption = options.get(correctIndex);
        Collections.shuffle(options);
        correctIndex = options.indexOf(correctOption);
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

    public static class Builder {
        private String text;
        private final List<String> options = new ArrayList<>();
        private int correctIndex = -1;
        private int points = 1;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder option(String option) {
            options.add(option);
            return this;
        }

        // adds the option and marks it as the correct one
        public Builder correctOption(String option) {
            options.add(option);
            correctIndex = options.size() - 1;
            return this;
        }

        public Builder points(int points) {
            this.points = points;
            return this;
        }

        public MultipleChoice build() {
            if (text == null || text.isBlank()) {
                throw new IllegalStateException("Multiple choice question needs a text");
            }
            if (options.size() < 2) {
                throw new IllegalStateException("Multiple choice question needs at least 2 options: " + text);
            }
            if (correctIndex < 0) {
                throw new IllegalStateException("Multiple choice question needs a correct option: " + text);
            }
            if (new HashSet<>(options).size() != options.size()) {
                throw new IllegalStateException("Multiple choice options must be different: " + text);
            }
            if (points <= 0) {
                throw new IllegalStateException("Points must be positive: " + text);
            }
            return new MultipleChoice(this);
        }
    }
}
