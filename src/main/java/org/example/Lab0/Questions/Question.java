package org.example.Lab0.Questions;

// O (Open/Closed): new question types implement this interface
// existing code (QuizRunner, ScoreCalculator) does not change
public interface Question {
    String getText();
    String display();
    boolean check(String answer);
    int getPoints();
}
