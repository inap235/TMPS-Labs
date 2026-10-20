package org.example.Lab1.App;

import org.example.Lab1.Sources.InMemorySource;
import org.example.Lab1.Sources.QuestionSource;
import org.example.Lab1.View.ConsoleView;
import org.example.Lab1.View.QuizView;

public class ConsoleQuizApp extends QuizApp {

    @Override
    protected QuizView createView() {
        return new ConsoleView();
    }

    @Override
    protected QuestionSource createSource() {
        return new InMemorySource();
    }
}
