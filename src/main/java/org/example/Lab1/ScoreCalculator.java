package org.example.Lab1;

import org.example.Lab1.Questions.Question;

import java.util.List;

// S (Single Responsibility): only scoring lives here.
public class ScoreCalculator {
    private int score = 0;

    public void addAnswer(Question question, boolean correct) {
        if (correct) {
            score += question.getPoints();
        }
    }

    public int getScore() {
        return score;
    }

    public int getMaxScore(List<Question> questions) {
        int max = 0;
        for (Question q : questions) {
            max += q.getPoints();
        }
        return max;
    }

    // called before every attempt so a retake starts from 0
    public void reset() {
        score = 0;
    }
}
