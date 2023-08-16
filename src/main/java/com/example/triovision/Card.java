package com.example.triovision;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Card {
    private ArrayList<String> listColors = new ArrayList<String>();
    private ArrayList locations = new ArrayList();
    Pawn[][] pattern = new Pawn[4][4];
    int CARD_SIZE = 4;
    public Card() {
        initializeColors();
    }

    public void initializeLocations(){
        locations.clear();
        for(int i = 0 ; i< 4 ;i ++){
            for(int j = 0; j < 4; j ++){
                locations.add(new int[]{i, j});
            }
        }
    }

    // Getter method for the pattern.
    public Pawn[][] getPattern() {
        return pattern;
    }

    public void cleanCard(GridPane gridPane) {
        gridPane.getChildren().clear();
    }
    public void changeCard(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        cleanCard(gridPane);
        initializeGameBoard(gridPane);
        initializeColors();
        initializeLocations();

        pattern = new Pawn[4][4];

        ArrayList<String> listOfColors = listColors;
        Random rand = new Random();
        String[] arrayColor = new String[3];


        for(int i = 0; i < 3; i ++){
            int choice = rand.nextInt(listOfColors.size());
            arrayColor[i] = listOfColors.get(choice);

            int locNumber = rand.nextInt(locations.size());
            int[] element = (int[]) locations.get(locNumber);

            pattern[element[1]][element[0]] = new Pawn(arrayColor[i]);

            locations.remove(locNumber);
            listOfColors.remove(choice);


            Circle pawnCircle = new Circle(20, Color.valueOf(arrayColor[i]));
            gridPane.add(pawnCircle, element[0],element[1]);
        }


    }

    private void initializeColors(){
        listColors.clear();
        listColors.add("Red");
        listColors.add("Red");
        listColors.add("Green");
        listColors.add("Green");
        listColors.add("Blue");
        listColors.add("Blue");
        listColors.add("Yellow");
        listColors.add("Yellow");
    }

    public void initializeGameBoard(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        for (int row = 0; row < CARD_SIZE; row++) {
            for (int col = 0; col < CARD_SIZE; col++) {
                // Create a white rectangle for the cell background
                Rectangle cellRectangle = new Rectangle(50, 50, Color.WHITE);
                cellRectangle.setStroke(Color.BLACK);
                gridPane.add(cellRectangle, col, row);

            }
        }
    }
}