package org.example.Lab1.View;
import org.example.Lab1.Questions.Question;
import javax.swing.JOptionPane;
public class SwingView implements QuizView {
    private String currentQuestion;

    public void showQuestion(int number, Question question) {
        currentQuestion = "Question " + number + ":\n" + question.display();
    }
    public String readAnswer() {
        String a = JOptionPane.showInputDialog(null, currentQuestion);
        return a == null ? "" : a;   // null if the user presses Cancel
    }
    public void showFeedback(boolean correct) {
        JOptionPane.showMessageDialog(null, correct ? "Correct!" : "Wrong.");
    }
    public void showScore(int score, int maxScore) {
        JOptionPane.showMessageDialog(null, "Your score: " + score + " / " + maxScore);
    }
    public boolean askRetake() {
        int choice = JOptionPane.showConfirmDialog(null, "Retake the quiz with shuffled questions?",
                "Retake", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }
}
