package com.example.triovision;

public class Pawn {
    private String color;
    private int x;
    private int y;

    public Pawn(String color) {
        this.color = color;
    }

    // Getters and setters for color, x, and y.
    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}