package com.midterm_lab;

import java.io.*;
import java.net.*;

import com.midterm_lab.model.*;
import com.midterm_lab.service.*;

public class Server {
    public static void main(String[] args) {
        int port = 8888;

        UserService.loadFromJson();
        System.out.println("Waiting for Players...");

        try (ServerSocket server = new ServerSocket(port)) {

            // For Player 1
            Socket client1 = server.accept();
            PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            System.out.println("Player 1 connected...");
            out1.println(">>>>> PLAYER 1");

            // For Player 2
            Socket client2 = server.accept();
            PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            System.out.println("Player 2 connected...");
            out2.println(">>>>> PLAYER 2");

            Player p1 = handler(in1, out1);
            Player p2 = handler(in2, out2);

            while (true) {

                out1.println("MENU");
                out2.println("MENU");

                String p1choice = in1.readLine();
                if ("2".equals(p1choice)) {
                    out1.println("LOGOUT");
                    out2.println("LOGOUT");
                    System.out.println("LOGOUT");
                    break;
                }

                // FOrward Choice of Player 1
                out2.println("P1_CHOICE:" + p1choice);

                String p2choice = in2.readLine();

                if ("2".equals(p2choice)) {
                    out1.println("OPPONENT_LEFT");
                    out2.println("LOGOUT");
                    System.out.println("GAME ENDED");
                    break;
                }

                GameSession session = new GameSession(p1, p2);

                p1.addGamePlayed();
                p2.addGamePlayed();

                out1.println("GAME STARTS NOW");
                out2.println("GAME STARTS NOW");
                System.out.println("GAME STARTS NOW");

                String matchup = p1.getUsername() + " VS " + p2.getUsername();
                out1.println(matchup);
                out2.println(matchup);
                System.out.println(matchup);

                for (int i = 1; i <= 3; i++) {
                    
                    out1.println("Round: " + i + "/3");
                    out2.println("Round: " + i + "/3");

                    session.setP1move(GameService.getMove(in1.readLine()));
                    session.setP2move(GameService.getMove(in2.readLine()));

                    String result = session.playRound();
                    out1.println(result);
                    out2.println(result);
                    System.out.println(result);
                }

                out1.println("GAME_OVER");
                out2.println("GAME_OVER");
                System.out.println("GAME_OVER");

                String overallWinner = session.determineOverallWinner();
                out1.println(overallWinner);
                out2.println(overallWinner);
                System.out.println(overallWinner);

                String leaderboard = UserService.getLeaderboardByWinRate();
                for (String line : leaderboard.split("\n")) {
                    out1.println(line);
                    out2.println(line);
                    System.out.println(line);
                }

                out1.println("LB_END");
                out2.println("LB_END");

                UserService.saveToJson();
            }

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }

    // For Player Choice of Log in or Sign Up
    public static Player handler(BufferedReader in, PrintWriter out) throws IOException {
        Player p = null;

        while (p == null) {
            
            String menuChoice = in.readLine();
            String username = in.readLine();
            String password = in.readLine();

            String response;
            if (menuChoice.equals("1")) {
                response = UserService.login(username, password);
            } else {
                response = UserService.signUp(username, password);
            }

            out.println(response);

            if (response.contains("Welcome") || response.contains("successfully")) {

                for (Player player : UserService.players) {
                    if (player.getUsername().equals(username)) {
                        p = player;
                        break; 
                    }
                }
            }
        }

        return p;
    }
}