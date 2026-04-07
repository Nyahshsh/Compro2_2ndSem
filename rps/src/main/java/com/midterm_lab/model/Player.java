package com.midterm_lab.model;

// Encapsulation
public class Player {
    // Private Fields
    private String username; // For identification
    private String password; // For security
    private int wins; // wins of every match

    // Constructor: called when loading from json or creating a new account
    public Player(String username, String password, int wins) {
        this.username = username;
        this.password = password;
        this.wins = wins;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
    public void incrementWins() {
        this.wins++;
    }

    public void incrementGamesPlayed() {
        
    }

    public int GamesPlayed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GamesPlayed'");
    }
}