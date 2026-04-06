package com.midterm_lab.service;

import com.midterm_lab.model.GameMove;
import com.midterm_lab.model.Paper;
import com.midterm_lab.model.Player;
import com.midterm_lab.model.Rock;
import com.midterm_lab.model.Scissors;

public class GameService {
    
    //where input mapping is applied
    public static GameMove getMove(String input){
        return switch (input.trim()) { // trim to remove the extra whitespace from the input
            case "0" -> new Rock(); // 0 for rock
            case "1" -> new Paper(); // 1 for paper
            case "2" -> new Scissors(); // 2 for scissors
            default -> null; // returns null if invalid input
        }; 
    }

    //search the players list if username matches the users input
    public static Player findUsername(String username){
        for(Player p : UserService.players){
            if(p.getUsername().equals(username))
                return p;
        }
        return null;
    }
}