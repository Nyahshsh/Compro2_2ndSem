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
            Socket client1 = server.accept();
            PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            System.out.println("Player 1 connected...");
            out1.println("IDENTITY: PLAYER 1");

            Socket client2 = server.accept();
            PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            System.out.println("Player 2 connected...");
            out2.println("IDENTITY: PLAYER 2");

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

                out2.println("P1_CHOICE:" + p1choice);

                String p2choice = in2.readLine();
                if ("2".equals(p2choice)) {
                    out1.println("OPPONENT_LEFT");
                    out2.println("LOGOUT");
                    System.out.println("GAME ENDED");
                    break;
                }

                GameSession session = new GameSession(p1, p2);
                p1.incrementGamesPlayed();
                p2.incrementGamesPlayed();

                out1.println("GAME STARTS NOW");
                out2.println("GAME STARTS NOW");
                System.out.println("GAME STARTS NOW");
                out1.println(p1.getUsername() + " VS " + p2.getUsername());
                out2.println(p1.getUsername() + " VS " + p2.getUsername());
                System.out.println(p1.getUsername() + " VS " + p2.getUsername());

                for (int i = 1; i <= 10; i++) {
                    out1.println("Round: " + i + "/10");
                    out2.println("Round: " + i + "/10");

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

                String leading = UserService.getLeaderboardByWinRate();
                for (String line : leading.split("\n")) {
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

    public static Player handler(BufferedReader in, PrintWriter out) throws IOException {
        Player p = null;
        while (p == null) {
            String menu1Choice = in.readLine();
            String username = in.readLine();
            String password = in.readLine();
            String response = (menu1Choice.equals("1")) ? UserService.login(username, password)
                    : UserService.signUp(username, password);
            out.println(response);
            if (response.contains("Welcome") || response.contains("successfully")) {
                p = UserService.players.stream()
                        .filter(player -> player.getUsername().equals(username))
                        .findFirst()
                        .orElse(null);
            }
        }
        return p;
    }
}