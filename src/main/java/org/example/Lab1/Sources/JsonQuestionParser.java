package org.example.Lab1.Sources;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.Lab1.Questions.FillInBlank;
import org.example.Lab1.Questions.MultipleChoice;
import org.example.Lab1.Questions.Question;
import org.example.Lab1.Questions.TrueFalse;

// S (Single Responsibility): only turns one JSON object into a Question.
// The builders do the validation, so a bad file fails with a clear message.
public class JsonQuestionParser {

    public Question parse(JsonNode node) {
        String type = required(node, "type").asText();
        return switch (type) {
            case "multiple_choice" -> parseMultipleChoice(node);
            case "true_false" -> new TrueFalse.Builder()
                    .text(required(node, "text").asText())
                    .answer(required(node, "answer").asBoolean())
                    .points(node.path("points").asInt(1))
                    .build();
            case "fill_in_blank" -> new FillInBlank.Builder()
                    .text(required(node, "text").asText())
                    .answer(required(node, "answer").asText())
                    .points(node.path("points").asInt(1))
                    .build();
            default -> throw new IllegalArgumentException("Unknown question type: " + type);
        };
    }

    private Question parseMultipleChoice(JsonNode node) {
        String answer = required(node, "answer").asText();
        MultipleChoice.Builder builder = new MultipleChoice.Builder()
                .text(required(node, "text").asText())
                .points(node.path("points").asInt(1));
        for (JsonNode option : required(node, "options")) {
            String text = option.asText();
            if (text.equals(answer)) {
                builder.correctOption(text);
            } else {
                builder.option(text);
            }
        }
        return builder.build();
    }

    private JsonNode required(JsonNode node, String field) {
        JsonNode value = node.get(field);
        if (value == null || value.isNull()) {
            throw new IllegalArgumentException("Question is missing \"" + field + "\": " + node);
        }
        return value;
    }
}
