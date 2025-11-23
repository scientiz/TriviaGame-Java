TriviaGame (JavaFX)

  - A lightweight JavaFX trivia game built for COSC 211.
  - Loads questions from a serialized .dat file, shuffles them, displays one at a time, and scores your answers. Simple, readable, and easy to extend.


Features
  - JavaFX UI (GridPane layout, labels, input fields, buttons)
  - Serialized question bank (trivia.dat)
  - Separate file-builder utility
  - Random question order
  - One–two word input validation
  - Score updates as you answer
  - Final score summary screen


Requirements
      
  1. Java 17+
  2. JavaFX 17 SDK

  
    (Example path: C:\Program Files\Java\javafx-sdk-17.0.17)


Running the Game

  1. Add VM Options (required for JavaFX)
  
      Go to: Project Properties → Run → VM Options
      
      Paste:

    --module-path "C:\Program Files\Java\javafx-sdk-17.0.17\lib" --add-modules=javafx.controls,javafx.fxml

     
  2. Build the question bank
     
      Run:

       TriviaFileBuilder.java
     
       This creates trivia.dat.


  4. Start the game
      
      Run: TriviaGameApp.java

    The JavaFX window will open with the first randomized question.
    
    
  MIT License.
