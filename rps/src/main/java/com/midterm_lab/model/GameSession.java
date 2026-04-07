package com.midterm_lab.model;

// Encapsulation
// Handles the Logic w/ the Players
public class GameSession {

    // Private Fields
    private Player p1;
    private Player p2;
    private int p1Score;
    private int p2Score;
    private GameMove p1move;
    private GameMove p2move;

    // Constructor
    public GameSession(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p1Score = 0;
        this.p2Score = 0;
    }

    // Getters and Setters
    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public int getP1Score() {
        return p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }

    public GameMove getP1move() {
        return p1move;
    }

    public GameMove getP2move() {
        return p2move;
    }

    public void setP1move(GameMove move) {
        this.p1move = move;
    }

    public void setP2move(GameMove move) {
        this.p2move = move;
    }

    // Will determine the winner every round (Logic of Game)
    public String playRound() {
        String moveDetails = p1.getUsername() + " picked " + p1move.getMoveName() + " | " +
                p2.getUsername() + " picked " + p2move.getMoveName() + "\n";

        int roundResult = p1move.compare(p2move);
        switch (roundResult) {
            case 0 -> {
                return moveDetails + "Result: Draw";
            }
            case 1 -> {
                p1Score++;
                return moveDetails + "Result: " + p1.getUsername() + " wins this round";
            }
            default -> {
                p2Score++;
                return moveDetails + "Result: " + p2.getUsername() + " wins this round";
            }
        }
    }

    // Will determine the winner after 3 rounds
    public String determineOverallWinner() {
        if (p1Score > p2Score) {
            p1.incrementWins();
            return p1.getUsername() + " wins the match!";
        } else if (p2Score > p1Score) {
            p2.incrementWins();
            return p2.getUsername() + " wins the match!";
        } else {
            return "Match is a draw!";
        }
    }
}