package org.example.Lab0;

import org.example.Lab0.Questions.InMemorySource;
import org.example.Lab0.View.SwingView;

public class Main {
    public static void main(String[] args) {
        QuizRunner runner = new QuizRunner(
                new InMemorySource(),
                new SwingView(),
                new ScoreCalculator()
        );
        runner.run();
    }
}
