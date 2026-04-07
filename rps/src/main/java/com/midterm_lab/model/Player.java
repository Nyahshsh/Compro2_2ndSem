package com.midterm_lab.model;

public class Player {
    private String username;
    private String password;
    private int wins;
    private int gamesPlayed;

    // Player Info
    public Player(String username, String password, int wins) {
        this.username = username;
        this.password = password;
        this.wins = wins;
        this.gamesPlayed = 0;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getWins() {
        return wins;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void incrementWins() {
        this.wins++;
    }

    public void addGamePlayed() {
        this.gamesPlayed++;
    }

}