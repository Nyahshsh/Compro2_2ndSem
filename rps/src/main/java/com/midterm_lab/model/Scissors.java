package com.midterm_lab.model;

//inheritance
public class Scissors extends GameMove {
    // constructor
    public Scissors() { // it calls the parent constructor with move name "Scissors"
        super("Scissors");  //super("Scissors") sets the private moveName field in GameMove.
    }

    // demonstrates polymorphism
    // Override the abstract methods
    @Override
    public int compare(GameMove other) {
        if (other instanceof Paper)
            return 1; // Scissors beats paper
        if (other instanceof Rock)
            return -1; // rock beats Scissors
        return 0; // Scissors vs Scissors [tie]
    }

}