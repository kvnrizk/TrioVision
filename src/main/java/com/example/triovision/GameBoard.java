package com.example.triovision;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class GameBoard {
    private Pawn[][] board;
    private String[] transfer = {"", "", "", ""};
    private ArrayList<Button> buttonsClicked = new ArrayList<Button>();
    private String[] buttonColors = {"", ""};
    int BOARD_SIZE = 4;

    int buttonClicks = 0;

    public GameBoard() {
        board = new Pawn[4][4];
    }


    public void initializeBoard(GridPane gridPane, Card card, Player player1, Player player2, Text text,Text score1,Text score2, GridPane cardGridPane, Stage primaryStage) {
        gridPane.setAlignment(Pos.CENTER);
        Button btn = new Button("Clear Choices");
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                // Create a white rectangle for the cell background

                String color = "-fx-background-color: #d3d3d3; ";
                if( (row == 1 && col == 0) || (row == 2 && col==0)){
                    color  = "-fx-background-color: #ff0000; ";
                } else if ( (row == 0 && col == 1) || (row == 0 && col==2) ){
                    color  = "-fx-background-color: #00ff00; ";
                } else if ( (row == 1 && col == 3) || (row == 2 && col==3) ){
                    color  = "-fx-background-color: #ffff00; ";
                } else if ( (row == 3 && col == 1) || (row == 3 && col==2) ){
                    color  = "-fx-background-color: #0000ff; ";
                } else {
                    color = "-fx-background-color: #d3d3d3; ";
                }

                // Create a circle to represent the pawn
                Button button = new Button();
                int finalRow = row;
                int finalCol = col;

                String finalColor = color;
                button.setOnAction(event -> {
                    buttonClicks++;
                    if(transfer[0] == "" && buttonColors[0] == ""){
                        System.out.println("Tranfser 0");
                        transfer[0] = finalRow+ "";
                        transfer[1] = finalCol+"";
                        buttonColors[0] = finalColor;
                    }else {
                        System.out.println("Tranfser 1");
                        transfer[2] = finalRow+ "";
                        transfer[3] = finalCol+"";
                        buttonColors[1] = finalColor;
                    }
                    buttonsClicked.add(button);
                    checkClicks(card, gridPane, player1, player2, text, score1, score2, cardGridPane, primaryStage);
                });
                int r = 30;
                button.setShape(new Circle(r));
                button.setMinSize(2*r, 2*r);
                button.setMaxSize(2*r, 2*r);
                button.setMaxHeight(200);
                button.setStyle(color);

                gridPane.add(button, col, row);
            }
        }
    }
    public void initializeToZero(){
        buttonColors[0] = "";
        buttonColors[1] = "";

        buttonsClicked.clear();
        buttonClicks = 0;

        transfer[0] = "";
        transfer[1] = "";
        transfer[2] = "";
        transfer[3] = "";
    }
    public void checkClicks(Card card, GridPane gridPane, Player player1, Player player2, Text text, Text score1, Text score2, GridPane cardGridPane, Stage primaryStage){
        System.out.println(buttonClicks);

        System.out.println(buttonColors[0]);
        System.out.println(buttonColors[1]);
        if(buttonClicks < 2){
            return;
        }

        if((Objects.equals(buttonsClicked.get(0).getStyle(), "-fx-background-color: #d3d3d3; ") && Objects.equals(buttonsClicked.get(1).getStyle(), "-fx-background-color: #d3d3d3; ")) ||
                (!Objects.equals(buttonsClicked.get(0).getStyle(), "-fx-background-color: #d3d3d3; ") && !Objects.equals(buttonsClicked.get(1).getStyle(), "-fx-background-color: #d3d3d3; "))){

            System.out.println("Bad Choices");
            initializeToZero();
            return;
        }

            System.out.println("Good Choices");
            movePawn();
            isPatternPresent(card, gridPane, player1, player2, text, score1, score2, cardGridPane, primaryStage);
    }



    public void movePawn() {
        System.out.println(buttonsClicked.get(0).getStyle());
        System.out.println(buttonsClicked.get(1).getStyle());

        String button1 = buttonsClicked.get(0).getStyle();
        String button2 = buttonsClicked.get(1).getStyle();

        buttonsClicked.get(0).setStyle(button2);
        buttonsClicked.get(1).setStyle(button1);
        initializeToZero();
    }

    public void isPatternPresent(Card card, GridPane gridPane, Player player1, Player player2, Text text, Text score1, Text score2, GridPane cardGridPane, Stage primaryStage) {
        Pawn[][] boardPawns = new Pawn[BOARD_SIZE][BOARD_SIZE];

        // Extract the pawn colors from the board positions
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button button = (Button) gridPane.getChildren().get(row * BOARD_SIZE + col);
                String buttonColor = button.getStyle();

                // Determine pawn color based on button style
                if (buttonColor.equals("-fx-background-color: #ff0000; ")) {
                    boardPawns[row][col] = new Pawn("Red");
                } else if (buttonColor.equals("-fx-background-color: #00ff00; ")) {
                    boardPawns[row][col] = new Pawn("Green");
                } else if (buttonColor.equals("-fx-background-color: #ffff00; ")) {
                    boardPawns[row][col] = new Pawn("Yellow");
                } else if (buttonColor.equals("-fx-background-color: #0000ff; ")) {
                    boardPawns[row][col] = new Pawn("Blue");
                } else {
                    boardPawns[row][col] = null; // No pawn on this position
                }
            }
        }

        // Compare the card's pattern with the board's pawn positions
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (card.getPattern()[row][col] != null) {
                    if (boardPawns[row][col] == null || !boardPawns[row][col].getColor().equals(card.getPattern()[row][col].getColor())) {
                        System.out.println("Incorrect");
                        return;
                    }
                }
            }
        }
        System.out.println("Correct");
        AddScore(player1, player2, text, score1, score2, card, cardGridPane, primaryStage);
    }

    void AddScore(Player player1, Player player2, Text text, Text score1, Text score2, Card card, GridPane cardGridPane, Stage primaryStage){

        if(text.getText().equals("Player 1")) {
            player1.addScore();
            score1.setText("Score Player 1:   "+ player1.score);

        }

        if(text.getText().equals("Player 2")) {
            player2.addScore();
            score2.setText("Score Player 2:   "+ player2.score);
        }

        card.changeCard(cardGridPane);

            if(player1.score >=3 ){
                Text winningText = new Text("Player 1 won!");
                winningText.setFont(Font.font(20)); // Set font size
                StackPane layout = new StackPane(winningText);
                Scene scene = new Scene(layout, 400, 300);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Game Over");
                primaryStage.show();
            }else if(player2.score >= 3){
                Text winningText = new Text("Player 2 won!");
                winningText.setFont(Font.font(20)); // Set font size
                StackPane layout = new StackPane(winningText);
                Scene scene = new Scene(layout, 400, 300);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Game Over");
                primaryStage.show();
            }

    }
}