/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Author: ScienTiz github.com/Scientiz
 * The main JavaFX app for a Batman trivia game in COSC 211.
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
 * JavaFX trivia game.
 *
 * Loads the questions from trivia.dat, shows them one at a time,
 * checks answers, handles scoring, and ends with a final score.
 */
public class TriviaGameApp extends Application {

    // Holds the questions after loading them from the file
    private ArrayList<Trivia> triviaList = new ArrayList<>();

    private int currentQuestionIndex = 0;
    private int totalScore = 0;

    // UI elements reused between method calls
    private Label questionLabel;
    private TextField answerField;
    private Label feedbackLabel;
    private Label scoreLabel;
    private Button submitButton;

    @Override
    public void start(Stage primaryStage) {

        // Load and randomize questions for each run
        loadTriviaFromFile();
        Collections.shuffle(triviaList);

        if (triviaList.isEmpty()) {
            System.out.println("No trivia questions loaded. Check trivia.dat.");
            return;
        }

        // StackPane wraps the GridPane because the assignment calls for it
        StackPane root = new StackPane();
        GridPane gamePane = buildGamePane();
        root.getChildren().add(gamePane);

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setTitle("The Dark Knight Trivia Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        showCurrentQuestion();
    }

    /**
     * Builds the main game layout. 
     * Simple, readable, and easy to modify later.
     */
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

        // Layout positions
        grid.add(questionLabel, 0, 0, 2, 1);
        grid.add(new Label("Your answer:"), 0, 1);
        grid.add(answerField, 1, 1);
        grid.add(submitButton, 1, 2);
        grid.add(feedbackLabel, 0, 3, 2, 1);
        grid.add(scoreLabel, 0, 4, 2, 1);

        // Submit button logic
        submitButton.setOnAction(e -> handleAnswer());

        return grid;
    }

    /**
     * Reads Trivia objects from trivia.dat until EOF.
     * This loads all questions into triviaList.
     */
    private void loadTriviaFromFile() {
    triviaList.clear();

    try (ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("trivia.dat"))) {

        while (true) {
            try {
                Object obj = in.readObject();

                // Old school instanceof + cast so Java 8+ is happy
                if (obj instanceof Trivia) {
                    Trivia t = (Trivia) obj;
                    triviaList.add(t);
                }

            } catch (EOFException eof) {
                // End of file, stop reading
                break;
            }
        }

    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error loading trivia.dat: " + e.getMessage());
    }
}


    /**
     * Shows the current question or ends the game if we’re out of questions.
     */
    private void showCurrentQuestion() {
        if (currentQuestionIndex >= triviaList.size()) {
            // Game is finished
            questionLabel.setText("Game over! Final score: " + totalScore);
            answerField.setDisable(true);
            submitButton.setDisable(true);
            feedbackLabel.setText("");
            return;
        }

        // Display the question text without revealing the answer
        Trivia current = triviaList.get(currentQuestionIndex);
        questionLabel.setText(current.displayQuestionOnly());

        answerField.clear();
        feedbackLabel.setText("");
    }

    /**
     * Handles answer checking, scoring, and moving to the next question.
     */
    private void handleAnswer() {
        String input = answerField.getText();

        // Don't accept blank entries
        if (input == null || input.trim().isEmpty()) {
            feedbackLabel.setText("Type something first.");
            return;
        }

        // Limit to one or two words like the assignment says
        String[] words = input.trim().split("\\s+");
        if (words.length > 2) {
            feedbackLabel.setText("Answer must be one or two words.");
            return;
        }

        Trivia current = triviaList.get(currentQuestionIndex);

        // Compare answers and update score
        if (current.isCorrect(input)) {
            int earned = current.calcPoints();
            totalScore += earned;
            feedbackLabel.setText("Correct! +" + earned + " point(s).");
        } else {
            feedbackLabel.setText("Incorrect. Correct answer: " + current.getAnswer());
        }

        scoreLabel.setText("Score: " + totalScore);

        // Move to the next question
        currentQuestionIndex++;
        showCurrentQuestion();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
