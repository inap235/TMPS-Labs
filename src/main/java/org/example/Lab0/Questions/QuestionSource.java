package org.example.Lab0.Questions;

import java.util.List;

//Dependency Inversion- QuizRunner depends on this abstraction not on where the questions actually come from
public interface QuestionSource {
    List<Question> getQuestions();
}
