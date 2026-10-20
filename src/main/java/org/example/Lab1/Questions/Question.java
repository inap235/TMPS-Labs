package org.example.Lab1.Questions;

// O (Open/Closed): new question types implement this interface
// existing code (QuizRunner, ScoreCalculator) does not change
public interface Question {
    String getText();
    String display();
    boolean check(String answer);
    int getPoints();

    // Prototype: every question can create an independent copy of itself
    Question copy();

    // only question types with options need to shuffle; the rest keep this no-op
    default void shuffleOptions() {
    }
}
