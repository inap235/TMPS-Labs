package org.example.Lab1;

import org.example.Lab1.App.QuizApp;
import org.example.Lab1.App.JavaFxJsonQuizApp;

public class Main {
    public static void main(String[] args) {
        // QuizApp app = new ConsoleQuizApp();
        QuizApp app = new JavaFxJsonQuizApp();
        app.start();
    }
}
