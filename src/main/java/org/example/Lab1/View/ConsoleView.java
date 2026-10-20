package org.example.Lab1.View;

import org.example.Lab1.Questions.Question;

import java.util.Scanner;

//Single Responsibility only console input/output lives here
public class ConsoleView implements QuizView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showQuestion(int number, Question question) {
        System.out.println();
        System.out.println("Question " + number + ": " + question.display());
        System.out.print("> ");
    }

    @Override
    public String readAnswer() {
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }

    @Override
    public void showFeedback(boolean correct) {
        System.out.println(correct ? "Correct!" : "Wrong.");
    }

    @Override
    public void showScore(int score, int maxScore) {
        System.out.println();
        System.out.println("Your score: " + score + " / " + maxScore);
    }

    @Override
    public boolean askRetake() {
        System.out.print("Retake the quiz? (y/n) > ");
        return readAnswer().trim().equalsIgnoreCase("y");
    }
}
