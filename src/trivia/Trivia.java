/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trivia;

import java.io.Serializable;

/**
 * Simple data class for a trivia question.
 */
public class Trivia implements Serializable {

    private static final long serialVersionUID = 1L;

    private String questionText;
    private String correctAnswer;
    private int pointValue;

    public Trivia() {
        this.questionText = "";
        this.correctAnswer = "";
        this.pointValue = 1;
    }

    public Trivia(String questionText, String correctAnswer, int pointValue) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        setPointValue(pointValue);
    }

    public String getQuestion() {
        return questionText;
    }

    public String getAnswer() {
        return correctAnswer;
    }

    public int getPoints() {
        return pointValue;
    }

    public void setQuestion(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setPointValue(int pointValue) {
        if (pointValue < 1) this.pointValue = 1;
        else if (pointValue > 3) this.pointValue = 3;
        else this.pointValue = pointValue;
    }

    public int calcPoints() {
        return pointValue;
    }

    public boolean isCorrect(String playerAnswer) {
        if (playerAnswer == null) return false;
        return correctAnswer.trim().equalsIgnoreCase(playerAnswer.trim());
    }

    @Override
    public String toString() {
        return "Question: " + questionText
                + " | Answer: " + correctAnswer
                + " | Points: " + pointValue;
    }
    
    public String displayQuestionOnly() {
    // Show the question with its point value, but not the answer
    return "Question (" + pointValue + " pts): " + questionText;
    }

}
