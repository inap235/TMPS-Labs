package org.example.Lab1.Sources;

import org.example.Lab1.Questions.FillInBlank;
import org.example.Lab1.Questions.MultipleChoice;
import org.example.Lab1.Questions.Question;
import org.example.Lab1.Questions.TrueFalse;

import java.util.List;

public class InMemorySource implements QuestionSource {

    @Override
    public List<Question> getQuestions() {
        return List.of(
                new MultipleChoice.Builder()
                        .text("Which SOLID letter says a class should have one reason to change?")
                        .option("Open/Closed")
                        .correctOption("Single Responsibility")
                        .option("Liskov Substitution")
                        .points(2)
                        .build(),
                new TrueFalse.Builder()
                        .text("Singleton is a creational design pattern.")
                        .answer(true)
                        .points(1)
                        .build(),
                new FillInBlank.Builder()
                        .text("The ___ pattern builds a complex object step by step.")
                        .answer("Builder")
                        .points(2)
                        .build(),
                new MultipleChoice.Builder()
                        .text("What is S in SOLID?")
                        .option("Sorry")
                        .option("Select")
                        .option("Straight")
                        .correctOption("Single Responsibility")
                        .points(2)
                        .build()
        );
    }
}
