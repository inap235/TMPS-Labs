package org.example.Lab0.View;
import org.example.Lab0.Questions.Question;

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
        return scanner.nextLine();
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
}
