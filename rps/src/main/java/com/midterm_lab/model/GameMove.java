package com.midterm_lab.model;

public abstract class GameMove {
    //encapsulation
    private String moveName;

    //constructor: when a subclass is created (Rock, Paper, Scissors)
    public GameMove(String moveName) {
        this.moveName = moveName;
    }

    //getter for moveName - to read moves name from outside
    public String getMoveName() {
        return moveName;
    }
    //abstract methods
    public abstract int compare(GameMove other); 
}