package org.example.Lab0.Questions;

import java.util.List;

public class InMemorySource implements QuestionSource {

    @Override
    public List<Question> getQuestions() {
        return List.of(
                new MultipleChoice("Which SOLID letter says a class should have one reason to change?",
                        List.of("Open/Closed", "Single Responsibility", "Liskov Substitution"), 1, 2),
                new TrueFalse("Singleton is a creational design pattern.", true, 1),
                new FillInBlank("The ___ pattern builds a complex object step by step.", "Builder", 2),
                new MultipleChoice("What is S in SOLID?",List.of("Sorry","Select","Straight","Single Responsibility"),3,2)
        );
    }
}
