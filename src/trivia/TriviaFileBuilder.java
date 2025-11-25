/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trivia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * One-time utility to create trivia.dat.
 * Run this ONCE before attempting to run the program: TriviaGameApp.java (main)
 * Run again only if edits are made. The JavaFX app always loads the created .dat file at runtime.
 */
public class TriviaFileBuilder {

    public static void main(String[] args) {

        // Build the 5 questions the game uses
        Trivia q1 = new Trivia("The less of them you have, the more one is worth?",
                "A friend", 2);

        Trivia q2 = new Trivia("I can be cracked. I can be made. I can be told. I can be played. What am I?",
                "A joke", 1);

        Trivia q3 = new Trivia("Who is Batman's alter ego?",
                "Bruce Wayne", 1);

        Trivia q4 = new Trivia("What villain is known for their plant-based powers?",
                "Poison Ivy", 2);

        Trivia q5 = new Trivia("What is the beginning of Eternity, The End of Time and Space, The Beginning of Every End, and The End of Every Race?",
                "E", 3);
        
        // I wanted to add more than requested for randomization
        Trivia q6 = new Trivia("What Catwoman's real name?",
                "Selena Kyle", 1);
        
        Trivia q7 = new Trivia("Who became Batgirl after being injured by the Joker?",
                "Barbara Gordon", 3);
        
        Trivia q8 = new Trivia("What role did Morgan Freeman play in The Dark Knight?",
                "Lucius Fox", 2);
        
        Trivia q9 = new Trivia("Which villain is known for his mastery of fear and gas that induces it?",
                "Scarecrow", 3);
        
        Trivia q10 = new Trivia("Who does the Arkham Asylum Psychiatrist - Dr. Harleen Quinzel, become?",
                "Harley Quinn", 1);

        // Store them in an array so writing is clean and predictable
        Trivia[] bank = { q1, q2, q3, q4, q5, q6, q7, q8, q9, q10 };

        // Write everything to trivia.dat using an ObjectOutputStream
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("trivia.dat"))) {

            for (Trivia t : bank) {
                out.writeObject(t); // writing each object sequentially
            }

            System.out.println("Successfully created trivia.dat.");

        } catch (IOException e) {
            System.out.println("Error writing trivia.dat: " + e.getMessage());
        }
    }
}
