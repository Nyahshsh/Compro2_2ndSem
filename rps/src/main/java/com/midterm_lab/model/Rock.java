package com.midterm_lab.model;

//inheritance
public class Rock extends GameMove {

    //constructor
    public Rock(){ // it calls the parent constructor with move name "Rock"
        super("Rock"); //super("Rock") sets the private moveName field in GameMove.
    }

    //demonstrates polymosphism
    //Override the abstract methods

    @Override
    public int compare(GameMove other) {
        if (other instanceof Scissors)
            return 1; // rock beats Scissors
        if (other instanceof Paper)
            return -1; // Paper beats rock
        return 0; // rock vs rock [tie]
    }
}