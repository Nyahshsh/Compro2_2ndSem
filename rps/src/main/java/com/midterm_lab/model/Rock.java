package com.midterm_lab.model;

// Inheritance
public class Rock extends GameMove {

    // Constructor
    public Rock(){ // It calls the parent constructor with move name "Rock"
        super("Rock"); 
    }

    //super("Rock") sets the private moveName field in GameMove.
    // Demonstrates polymorphism
    // Override the abstract methods
    @Override
    public int compare(GameMove other) {
        if (other instanceof Scissors)
            return 1;
        if (other instanceof Paper)
            return -1; 
        return 0; 
    }
}