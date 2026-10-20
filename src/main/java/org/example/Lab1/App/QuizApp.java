package org.example.Lab1.App;

import org.example.Lab1.QuizRunner;
import org.example.Lab1.ScoreCalculator;
import org.example.Lab1.Sources.QuestionSource;
import org.example.Lab1.View.QuizView;

// Factory Method: start() does the same work for every app, but the subclasses
// decide which concrete view and question source get created
public abstract class QuizApp {

    protected abstract QuizView createView();

    protected abstract QuestionSource createSource();

    public final void start() {
        QuizRunner runner = new QuizRunner(createSource(), createView(), new ScoreCalculator());
        runner.run();
    }
}
