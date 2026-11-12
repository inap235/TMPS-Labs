package org.example.Lab1.View;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Lab1.Questions.Question;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

// A window with radio buttons for the options and progress bars, made with JavaFX.
// JavaFX draws on its own thread and reacts to clicks, while QuizRunner expects methods that wait
// for the answer. Each method builds its screen on the JavaFX thread and then waits until a button
// completes the CompletableFuture, so QuizRunner works the same as with the console.
public class JavaFxView implements QuizView {
    private Stage stage;
    private int number;
    private int total;
    private Question question;

    // parts of the current question screen that showFeedback() changes
    private Node answerArea;
    private HBox footer;
    private Label feedback;

    private CompletableFuture<Boolean> retakeChoice;

    public JavaFxView() {
        CompletableFuture<Void> ready = new CompletableFuture<>();
        Platform.startup(() -> {
            Platform.setImplicitExit(false);
            stage = new Stage();
            stage.setTitle("Design Patterns Quiz");
            Scene scene = new Scene(new StackPane(), 680, 520);
            scene.getStylesheets().add(JavaFxView.class.getResource("/quiz.css").toExternalForm());
            stage.setScene(scene);
            // closing the window ends the quiz
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });
            stage.show();
            ready.complete(null);
        });
        ready.join();
    }

    @Override
    public void showQuestion(int number, int total, Question question) {
        this.number = number;
        this.total = total;
        this.question = question;
    }

    @Override
    public String readAnswer() {
        return waitFor(this::showQuestionScreen);
    }

    @Override
    public void showFeedback(boolean correct) {
        this.<Void>waitFor(done -> {
            answerArea.setDisable(true);
            feedback.setText(correct ? "Correct!" : "Wrong.");
            feedback.getStyleClass().add(correct ? "correct" : "wrong");
            Button next = primaryButton("Next", "next", () -> done.complete(null));
            footer.getChildren().setAll(feedback, spacer(), next);
            next.requestFocus();
        });
    }

    @Override
    public void showScore(int score, int maxScore) {
        retakeChoice = new CompletableFuture<>();
        Platform.runLater(() -> {
            Label title = styled(new Label("Quiz finished"), "title");
            Label result = styled(new Label(score + " / " + maxScore), "score");
            ProgressBar bar = new ProgressBar(maxScore == 0 ? 0 : (double) score / maxScore);
            bar.setMaxWidth(Double.MAX_VALUE);
            Label percent = styled(new Label(percent(score, maxScore) + "% correct"), "step");

            Button exit = new Button("Exit");
            exit.setId("exit");
            exit.setOnAction(e -> retakeChoice.complete(false));
            Button retake = primaryButton("Retake (shuffled)", "retake", () -> retakeChoice.complete(true));
            HBox buttons = new HBox(12, exit, retake);
            buttons.setAlignment(Pos.CENTER);

            VBox card = card(title, result, bar, percent, buttons);
            card.setAlignment(Pos.CENTER);
            show(card);
        });
    }

    @Override
    public boolean askRetake() {
        return retakeChoice.join();
    }

    @Override
    public void close() {
        Platform.runLater(stage::close);
        Platform.exit();
    }

    private void showQuestionScreen(CompletableFuture<String> answer) {
        Label step = styled(new Label("Question " + number + " of " + total), "step");
        ProgressBar progress = new ProgressBar((double) (number - 1) / total);
        progress.setMaxWidth(Double.MAX_VALUE);

        List<String> options = question.getOptions();
        Label text = styled(new Label(question.getText()), "question");
        text.setWrapText(true);

        Button submit;
        if (options.isEmpty()) {
            TextField field = new TextField();
            field.setId("answer-field");
            field.setPromptText("Type the missing word");
            submit = primaryButton("Submit", "submit", () -> answer.complete(field.getText()));
            submit.disableProperty().bind(field.textProperty().isEmpty());
            answerArea = field;
        } else {
            ToggleGroup group = new ToggleGroup();
            VBox choices = new VBox(10);
            for (int i = 0; i < options.size(); i++) {
                RadioButton option = new RadioButton(options.get(i));
                option.setId("option-" + (i + 1));
                option.setUserData(String.valueOf(i + 1));   // the answer check() expects
                option.setToggleGroup(group);
                option.setWrapText(true);
                choices.getChildren().add(option);
            }
            submit = primaryButton("Submit", "submit",
                    () -> answer.complete((String) group.getSelectedToggle().getUserData()));
            submit.disableProperty().bind(group.selectedToggleProperty().isNull());
            answerArea = choices;
        }

        feedback = styled(new Label(), "feedback");
        footer = new HBox(12, spacer(), submit);
        footer.setAlignment(Pos.CENTER_LEFT);

        show(card(step, progress, text, answerArea, footer));
        answerArea.requestFocus();
    }

    // runs the screen on the JavaFX thread and waits until it completes the result
    private <T> T waitFor(Consumer<CompletableFuture<T>> screen) {
        CompletableFuture<T> result = new CompletableFuture<>();
        Platform.runLater(() -> screen.accept(result));
        return result.join();
    }

    private void show(VBox card) {
        StackPane root = new StackPane(card);
        root.getStyleClass().add("background");
        stage.getScene().setRoot(root);
    }

    private VBox card(Node... children) {
        VBox card = new VBox(18, children);
        card.getStyleClass().add("card");
        card.setMaxWidth(580);
        card.setMaxHeight(Region.USE_PREF_SIZE);
        return card;
    }

    private Button primaryButton(String text, String id, Runnable action) {
        Button button = new Button(text);
        button.setId(id);
        button.getStyleClass().add("primary");
        button.setDefaultButton(true);   // Enter presses it
        button.setOnAction(e -> action.run());
        return button;
    }

    private static <T extends Label> T styled(T label, String styleClass) {
        label.getStyleClass().add(styleClass);
        return label;
    }

    private static Region spacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    private static int percent(int score, int maxScore) {
        return maxScore == 0 ? 0 : Math.round(100f * score / maxScore);
    }
}
