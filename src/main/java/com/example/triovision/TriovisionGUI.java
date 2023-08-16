package com.example.triovision;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

import java.util.Objects;

public class TriovisionGUI extends Application {
    private GameBoard gameBoard;
    private int currentPlayerIndex;
    private Card card;


    private Button[][] cellButtons;
    private Button nextCardButton;

    public TriovisionGUI() {
        currentPlayerIndex = 0;
    }

    // Implement methods to handle game flow, player turns, card drawing, and pattern matching.
    // You need to implement methods for:
    // - Drawing cards from the drawPile.
    // - Handling player turns (including input for moving pawns).
    // - Checking if the pattern on a card is present on the board.
    // - Ending the game when all cards have been played.
    // - Implementing the graphical interface using JavaFX.

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("TriColor Game");
        GridPane gridPane = new GridPane();
        GridPane cardGridPane = new GridPane();
        gameBoard = new GameBoard();
        card = new Card();
        card.initializeGameBoard(cardGridPane);
        card.changeCard(cardGridPane);

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Text text = new Text("Player 1");
        Text score1 = new Text("Score Player 1:   "+ player1.score);
        Text score2 = new Text("Score Player 2:   "+ player1.score);
        Text ScoreSeperator = new Text("");

        gameBoard.initializeBoard(gridPane, card, player1, player2, text, score1, score2, cardGridPane, primaryStage);

        Button swapPlayer = new Button("Swap Player");
        swapPlayer.setOnAction(actionEvent -> {
            if(Objects.equals(text.getText(), "Player 1")){
                text.setText("Player 2");
            }else if(Objects.equals(text.getText(), "Player 2")) {
                text.setText("Player 1");
            }
        });

        Button button = new Button("Clear Choices");
        button.setOnAction( actionEvent -> {
            gameBoard.initializeToZero();
        });

        Button buttonChangeCard = new Button("Change Card");
        buttonChangeCard.setOnAction( actionEvent -> {

            card.changeCard(cardGridPane);
        });
        GridPane root = new GridPane();

        root.setAlignment(Pos.CENTER);
        root.add(buttonChangeCard, 4,0);
        root.add(gridPane, 1,2);
        root.add(button, 0,0);
        root.add(cardGridPane, 4,2);
        root.add(text, 0, 1);
        root.add(swapPlayer, 1, 1);
        root.add(score1, 3, 3);
        root.add(score2, 5, 3);
        root.add(ScoreSeperator, 4,3);
        // Implement the JavaFX representation of the game board, pawns, and cards.

        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}