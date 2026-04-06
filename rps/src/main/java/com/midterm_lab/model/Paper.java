package com.midterm_lab.model;

//inheritance
public class Paper extends GameMove {

    // constructor
    public Paper() { // it calls the parent constructor with move name "Paper"
        super("Paper"); // super("Paper") sets the private moveName field in GameMove.
    }

    // demonstrates polymorphism
    // Override the abstract method
    @Override
    public int compare(GameMove other) {
        if (other instanceof Rock)
            return 1; // Paper beats rock
        if (other instanceof Scissors)
            return -1; // Scissors beats Paper 
        return 0; // Paper vs paper [tie]
    }

}