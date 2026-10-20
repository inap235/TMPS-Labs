package org.example.Lab1.Sources;

import org.example.Lab1.Questions.Question;

import java.util.List;

//Dependency Inversion- QuizRunner depends on this abstraction not on where the questions actually come from
public interface QuestionSource {
    List<Question> getQuestions();
}
