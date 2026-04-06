package com.midterm_lab.model;

//encapsulation
public class Player {
    // private fields
    private String username; // used for identification
    private String password; // for security
    private int wins; // wins every match

    // constructor: called when loadeing from json or creating a new account
    public Player(String username, String password, int wins) {
        this.username = username;
        this.password = password;
        this.wins = wins;
    }

    // getters and setters
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incrementGamesPlayed'");
    }
}