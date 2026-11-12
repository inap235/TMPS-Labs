package org.example.Lab1.View;

import org.example.Lab1.Questions.Question;

// D (Dependency Inversion): QuizRunner talks to the user only through this interface.
public interface QuizView {
    void showQuestion(int number, int total, Question question);
    String readAnswer();
    void showFeedback(boolean correct);
    void showScore(int score, int maxScore);
    boolean askRetake();

    // views that open a window or terminal close it here; the console has nothing to close
    default void close() {
    }
}
