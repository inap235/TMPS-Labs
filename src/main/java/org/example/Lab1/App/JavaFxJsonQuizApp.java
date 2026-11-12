package org.example.Lab1.App;

import org.example.Lab1.Sources.JsonSource;
import org.example.Lab1.Sources.QuestionSource;
import org.example.Lab1.View.JavaFxView;
import org.example.Lab1.View.QuizView;

public class JavaFxJsonQuizApp extends QuizApp {

    @Override
    protected QuizView createView() {
        return new JavaFxView();
    }

    @Override
    protected QuestionSource createSource() {
        return new JsonSource("solid.json", "creational-patterns.json", "oop-basics.json");
    }
}
