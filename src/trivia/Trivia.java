/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trivia;

import java.io.Serializable;

/**
 * Holds one trivia question. 
 * Simple data class the game reads from the file.
 * Serializable so we can save/load cleanly.
 */
public class Trivia implements Serializable {

    private static final long serialVersionUID = 1L;

    // Core fields for the game
    private String questionText;
    private String correctAnswer;
    private int pointValue;

    /**
     * Default setup. Empty question/answer with minimum points.
     */
    public Trivia() {
        this.questionText = "";
        this.correctAnswer = "";
        this.pointValue = 1;
    }

    /**
     * Main constructor. Use this when building the question bank.
     */
    public Trivia(String questionText, String correctAnswer, int pointValue) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        setPointValue(pointValue); // keeps points between 1–3
    }

    // ----- Getters -----

    public String getQuestion() {
        return questionText;
    }

    public String getAnswer() {
        return correctAnswer;
    }

    public int getPoints() {
        return pointValue;
    }

    // ----- Setters -----

    public void setQuestion(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Assignment mentions setPoints, so I keep this wrapper here.
     * It feeds into the actual validator below.
     */
    public void setPoints(int points) {
        setPointValue(points);
    }

    /**
     * Makes sure the point value always stays in the valid range.
     */
    public void setPointValue(int pointValue) {
        if (pointValue < 1) this.pointValue = 1;
        else if (pointValue > 3) this.pointValue = 3;
        else this.pointValue = pointValue;
    }

    /**
     * Helper for scoring. Just returns the stored point value.
     */
    public int calcPoints() {
        return pointValue;
    }

    /**
     * Checks if the player's answer matches the correct one.
     * Ignores capitalization and extra spacing.
     */
    public boolean isCorrect(String playerAnswer) {
        if (playerAnswer == null) return false;
        return correctAnswer.trim().equalsIgnoreCase(playerAnswer.trim());
    }

    /**
     * Used when debugging or printing the question list.
     */
    @Override
    public String toString() {
        return "Question: " + questionText
                + " | Answer: " + correctAnswer
                + " | Points: " + pointValue;
    }

    /**
     * What the game shows to the user (no answer).
     */
    public String displayQuestionOnly() {
        return "Question (" + pointValue + " pts): " + questionText;
    }
}
