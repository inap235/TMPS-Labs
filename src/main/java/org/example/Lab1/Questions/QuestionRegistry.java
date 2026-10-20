package org.example.Lab1.Questions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Prototype registry: keeps the original questions and only ever hands out copies,
// so nobody can change the originals (for example by shuffling options)
public class QuestionRegistry {
    private final Map<String, Question> prototypes = new LinkedHashMap<>();

    public void register(String key, Question prototype) {
        if (prototypes.containsKey(key)) {
            throw new IllegalArgumentException("Question already registered: " + key);
        }
        prototypes.put(key, prototype);
    }

    public Question get(String key) {
        Question prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("No question registered as: " + key);
        }
        return prototype.copy();
    }

    public List<Question> getAll() {
        List<Question> copies = new ArrayList<>();
        for (Question prototype : prototypes.values()) {
            copies.add(prototype.copy());
        }
        return copies;
    }

    public int size() {
        return prototypes.size();
    }
}
