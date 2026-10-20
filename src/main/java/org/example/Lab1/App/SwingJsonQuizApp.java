package org.example.Lab1.App;

import org.example.Lab1.Sources.JsonSource;
import org.example.Lab1.Sources.QuestionSource;
import org.example.Lab1.View.QuizView;
import org.example.Lab1.View.SwingView;

public class SwingJsonQuizApp extends QuizApp {

    @Override
    protected QuizView createView() {
        return new SwingView();
    }

    @Override
    protected QuestionSource createSource() {
        return new JsonSource("solid.json", "creational-patterns.json", "oop-basics.json");
    }
}
