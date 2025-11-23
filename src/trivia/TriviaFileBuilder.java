/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trivia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is only used once to create trivia.dat.
 * After running it a single time, the JavaFX game will read
 * the questions from the file instead of hardcoding them.
 */
public class TriviaFileBuilder {

    public static void main(String[] args) {

        Trivia q1 = new Trivia(
                "What planet is known as the Red Planet?",
                "Mars",
                1);

        Trivia q2 = new Trivia(
                "What is the largest mammal on Earth?",
                "Blue whale",
                1);

        Trivia q3 = new Trivia(
                "In what year did the Titanic sink?",
                "1912",
                2);

        Trivia q4 = new Trivia(
                "What gas do plants absorb from the atmosphere?",
                "Carbon dioxide",
                2);

        Trivia q5 = new Trivia(
                "Who developed the theory of general relativity?",
                "Einstein",
                3);

        Trivia[] bank = { q1, q2, q3, q4, q5 };

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("trivia.dat"))) {

            for (Trivia t : bank) {
                out.writeObject(t);
            }

            System.out.println("Successfully created trivia.dat.");
        } catch (IOException e) {
            System.out.println("Error writing trivia.dat: " + e.getMessage());
        }
    }
}
