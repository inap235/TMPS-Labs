package org.example.Lab1;

import org.example.Lab1.Questions.Question;
import org.example.Lab1.Sources.QuestionSource;
import org.example.Lab1.View.QuizView;

import java.util.ArrayList;
import java.util.Collections;
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
        List<Question> questions = new ArrayList<>(source.getQuestions());
        while (true) {
            playOnce(questions);
            if (!view.askRetake()) {
                break;
            }
            // Prototype: the source hands out fresh copies, so shuffling them
            // never changes the original questions
            questions = new ArrayList<>(source.getQuestions());
            Collections.shuffle(questions);
            questions.forEach(Question::shuffleOptions);
        }
    }

    private void playOnce(List<Question> questions) {
        calculator.reset();
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
