package org.example.Lab1;

import org.example.Lab1.App.QuizApp;
import org.example.Lab1.App.SwingJsonQuizApp;

public class Main {
    public static void main(String[] args) {
        // to use the console and the built-in questions instead: new ConsoleQuizApp()
        QuizApp app = new SwingJsonQuizApp();
        app.start();
    }
}
