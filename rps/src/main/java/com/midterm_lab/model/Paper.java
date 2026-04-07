package com.midterm_lab.model;

// Inheritance
public class Paper extends GameMove {

    // Constructor
    public Paper() { 
        super("Paper"); 
    }
    
    // super("Paper") sets the private moveName field in GameMove.
    // Demonstrates Polymorphism
    // Override the abstract method
    @Override
    public int compare(GameMove other) {
        if (other instanceof Rock)
            return 1;
        if (other instanceof Scissors)
            return -1; 
        return 0; 
    }

}