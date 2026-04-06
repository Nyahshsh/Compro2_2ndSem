package com.midterm_lab.model;

// encapsulation
// manages the round match logic between two players
public class GameSession {
    // private fields: two players in the session
    private Player p1;
    private Player p2;
    private int p1Score;
    private int p2Score;
    private GameMove p1move;
    private GameMove p2move;

    // constructor
    public GameSession(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p1Score = 0;
        this.p2Score = 0;
    }

    // getters and setters
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

    // determine the winner every round, this is the logic of the game
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

    // determine the winner aftr 10 rounds
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