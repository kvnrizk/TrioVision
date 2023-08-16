package com.example.triovision;

public class Player {

    String name;
    int score = 0;

    public Player(String name){
        this.name = name;
    }

    public int addScore(){
        this.score += 1;
        return score;
    }
}
