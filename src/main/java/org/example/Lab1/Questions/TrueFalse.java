package org.example.Lab1.Questions;

public class TrueFalse implements Question {
    private final String text;
    private final boolean correct;
    private final int points;

    // Builder: objects are created only through TrueFalse.Builder
    private TrueFalse(Builder builder) {
        this.text = builder.text;
        this.correct = builder.correct;
        this.points = builder.points;
    }

    // Prototype: all fields are immutable, so copying them is enough
    private TrueFalse(TrueFalse other) {
        this.text = other.text;
        this.correct = other.correct;
        this.points = other.points;
    }

    @Override
    public Question copy() {
        return new TrueFalse(this);
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

    public static class Builder {
        private String text;
        private Boolean correct;
        private int points = 1;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder answer(boolean correct) {
            this.correct = correct;
            return this;
        }

        public Builder points(int points) {
            this.points = points;
            return this;
        }

        public TrueFalse build() {
            if (text == null || text.isBlank()) {
                throw new IllegalStateException("True/false question needs a text");
            }
            if (correct == null) {
                throw new IllegalStateException("True/false question needs an answer: " + text);
            }
            if (points <= 0) {
                throw new IllegalStateException("Points must be positive: " + text);
            }
            return new TrueFalse(this);
        }
    }
}
