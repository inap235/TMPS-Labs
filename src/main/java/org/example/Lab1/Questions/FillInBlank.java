package org.example.Lab1.Questions;

public class FillInBlank implements Question {
    private final String text;
    private final String correctAnswer;
    private final int points;

    // Builder: objects are created only through FillInBlank.Builder
    private FillInBlank(Builder builder) {
        this.text = builder.text;
        this.correctAnswer = builder.correctAnswer;
        this.points = builder.points;
    }

    // Prototype: all fields are immutable, so copying them is enough
    private FillInBlank(FillInBlank other) {
        this.text = other.text;
        this.correctAnswer = other.correctAnswer;
        this.points = other.points;
    }

    @Override
    public Question copy() {
        return new FillInBlank(this);
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

    public static class Builder {
        private String text;
        private String correctAnswer;
        private int points = 1;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder answer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        public Builder points(int points) {
            this.points = points;
            return this;
        }

        public FillInBlank build() {
            if (text == null || text.isBlank()) {
                throw new IllegalStateException("Fill in the blank question needs a text");
            }
            if (correctAnswer == null || correctAnswer.isBlank()) {
                throw new IllegalStateException("Fill in the blank question needs an answer: " + text);
            }
            if (points <= 0) {
                throw new IllegalStateException("Points must be positive: " + text);
            }
            return new FillInBlank(this);
        }
    }
}
