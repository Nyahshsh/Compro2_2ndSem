package com.midterm_lab.model;

public abstract class GameMove {

    // Encapsulation
    private String moveName;

    // Constructor for the subclasses(Rock, Paper, Scissors)
    public GameMove(String moveName) {
        this.moveName = moveName;
    }

    // Getter for moveName
    public String getMoveName() {
        return moveName;
    }

    // Abstract Method
    public abstract int compare(GameMove other); 
}