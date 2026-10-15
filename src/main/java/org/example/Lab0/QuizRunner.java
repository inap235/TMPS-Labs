package org.example.Lab0;

import org.example.Lab0.Questions.Question;
import org.example.Lab0.Questions.QuestionSource;
import org.example.Lab0.View.QuizView;

import java.util.List;

// S (Single Responsibility): only the quiz flow lives here
// D (Dependency Inversion): depends on QuestionSource and QuizView interfaces
public class QuizRunner {
    private final QuestionSource source;
    private final QuizView view;
    private final ScoreCalculator calculator;

    public QuizRunner(QuestionSource source, QuizView view, ScoreCalculator calculator) {
        this.source = source;
        this.view = view;
        this.calculator = calculator;
    }

    public void run() {
        List<Question> questions  = source.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            view.showQuestion(i + 1, question);
            boolean correct = question.check(view.readAnswer());
            view.showFeedback(correct);
            calculator.addAnswer(question, correct);
        }
        view.showScore(calculator.getScore(), calculator.getMaxScore(questions));
    }
}
