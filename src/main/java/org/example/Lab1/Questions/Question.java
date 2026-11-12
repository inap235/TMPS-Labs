package org.example.Lab1.Questions;

import java.util.List;

// O (Open/Closed): new question types implement this interface
// existing code (QuizRunner, ScoreCalculator) does not change
public interface Question {
    String getText();
    String display();
    boolean check(String answer);
    int getPoints();

    // the choices a graphical view can show as buttons; the answer is the chosen number ("1", "2", ...).
    // An empty list means the user types the answer.
    default List<String> getOptions() {
        return List.of();
    }

    // Prototype: every question can create an independent copy of itself
    Question copy();

    // only question types with options need to shuffle; the rest keep this no-op
    default void shuffleOptions() {
    }
}
