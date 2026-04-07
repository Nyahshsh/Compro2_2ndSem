package com.midterm_lab.service;

import com.midterm_lab.model.GameMove;
import com.midterm_lab.model.Paper;
import com.midterm_lab.model.Player;
import com.midterm_lab.model.Rock;
import com.midterm_lab.model.Scissors;

public class GameService {

    public static GameMove getMove(String input) {
        
        if (input == null) {
            return null;
        }

        String trimmed = input.trim();

        if (trimmed.equals("0")) {
            return new Rock();
        } else if (trimmed.equals("1")) {
            return new Paper();
        } else if (trimmed.equals("2")) {
            return new Scissors();
        } else {
            return null;
        }
    }

    // Find Username
    public static Player findUsername(String username) {
        for (Player p : UserService.players) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null; // If Player not found
    }
}