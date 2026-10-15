package org.example.Lab0.View;

import org.example.Lab0.Questions.Question;

// D (Dependency Inversion): QuizRunner talks to the user only through this interface.
public interface QuizView {
    void showQuestion(int number, Question question);
    String readAnswer();
    void showFeedback(boolean correct);
    void showScore(int score, int maxScore);
}
