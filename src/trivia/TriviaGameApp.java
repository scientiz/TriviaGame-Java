/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trivia;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Main JavaFX app for the trivia game.
 * This handles loading the trivia.dat file, showing the questions
 * one at a time, scoring the answers, and ending with a final score.
 * Will clean it up visually later once logic is confirmed working.
 */
public class TriviaGameApp extends Application {

    private ArrayList<Trivia> triviaList = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int totalScore = 0;

    // UI controls
    private Label questionLabel;
    private TextField answerField;
    private Label feedbackLabel;
    private Label scoreLabel;
    private Button submitButton;

    @Override
    public void start(Stage primaryStage) {

        loadTriviaFromFile();
        Collections.shuffle(triviaList);

        if (triviaList.isEmpty()) {
            System.out.println("No trivia questions loaded. Check trivia.dat.");
            return;
        }

        StackPane root = new StackPane();
        GridPane gamePane = buildGamePane();
        root.getChildren().add(gamePane);

        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("Trivia Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        showCurrentQuestion();
    }

    private GridPane buildGamePane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(12);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        questionLabel = new Label("Question goes here...");
        answerField = new TextField();
        answerField.setPromptText("Type your answer (1–2 words)");

        submitButton = new Button("Submit");
        feedbackLabel = new Label();
        scoreLabel = new Label("Score: 0");

        grid.add(questionLabel, 0, 0, 2, 1);
        grid.add(new Label("Your answer:"), 0, 1);
        grid.add(answerField, 1, 1);
        grid.add(submitButton, 1, 2);
        grid.add(feedbackLabel, 0, 3, 2, 1);
        grid.add(scoreLabel, 0, 4, 2, 1);

        submitButton.setOnAction(e -> handleAnswer());

        return grid;
    }

    private void loadTriviaFromFile() {
        triviaList.clear();

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("trivia.dat"))) {

            while (true) {
                try {
                    Object obj = in.readObject();
                    if (obj instanceof Trivia) {
                        triviaList.add((Trivia) obj);
                    }
                } catch (EOFException eof) {
                    break; // done reading file
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading trivia.dat: " + e.getMessage());
        }
    }

    private void showCurrentQuestion() {
        if (currentQuestionIndex >= triviaList.size()) {
            questionLabel.setText("Game over! Final score: " + totalScore);
            answerField.setDisable(true);
            submitButton.setDisable(true);
            feedbackLabel.setText("");
            return;
        }

        Trivia current = triviaList.get(currentQuestionIndex);
        questionLabel.setText(current.displayQuestionOnly());
        answerField.clear();
        feedbackLabel.setText("");
    }

    private void handleAnswer() {
        String input = answerField.getText();

        if (input == null || input.trim().isEmpty()) {
            feedbackLabel.setText("Type something first.");
            return;
        }

        String[] words = input.trim().split("\\s+");
        if (words.length > 2) {
            feedbackLabel.setText("Answer must be one or two words.");
            return;
        }

        Trivia current = triviaList.get(currentQuestionIndex);

        if (current.isCorrect(input)) {
            int earned = current.calcPoints();
            totalScore += earned;
            feedbackLabel.setText("Correct! +" + earned + " point(s).");
        } else {
            feedbackLabel.setText("Incorrect. Correct answer: " + current.getAnswer());
        }

        scoreLabel.setText("Score: " + totalScore);
        currentQuestionIndex++;
        showCurrentQuestion();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
