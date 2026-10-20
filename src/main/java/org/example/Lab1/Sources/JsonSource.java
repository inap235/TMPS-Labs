package org.example.Lab1.Sources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Lab1.Questions.Question;
import org.example.Lab1.Questions.QuestionRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;

// Reads quizzes from JSON files in src/main/resources/quizzes.
// Files are read and parsed only once; the parsed questions are kept as prototypes
// and every call to getQuestions() returns fresh copies of them.
public class JsonSource implements QuestionSource {
    private static final String FOLDER = "/quizzes/";

    private final ObjectMapper mapper = new ObjectMapper();
    private final JsonQuestionParser parser = new JsonQuestionParser();
    private final QuestionRegistry registry = new QuestionRegistry();

    public JsonSource(String... fileNames) {
        for (String fileName : fileNames) {
            load(fileName);
        }
    }

    private void load(String fileName) {
        try (InputStream in = JsonSource.class.getResourceAsStream(FOLDER + fileName)) {
            if (in == null) {
                throw new IllegalArgumentException("Quiz file not found: " + FOLDER + fileName);
            }
            JsonNode root = mapper.readTree(in);
            int index = 1;
            for (JsonNode node : root.path("questions")) {
                registry.register(fileName + "#" + index, parser.parse(node));
                index++;
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Could not read quiz file " + fileName, e);
        }
    }

    @Override
    public List<Question> getQuestions() {
        return registry.getAll();
    }
}
