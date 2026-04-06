package com.rps.waraferek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 7777;
        UserService.loadFromJson();

        System.out.println("Waiting for Players");

        try (ServerSocket server = new ServerSocket(port)) {

            Socket client1 = server.accept();
            PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            System.out.println("Player 1 connected."); // SERVER ONLY
            out1.println("IDENTITY: PLAYER 1"); // send to player 1

            Socket client2 = server.accept();
            PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            System.out.println("Player 2 connected."); // SERVER ONLY
            out2.println("IDENTITY: PLAYER 2"); // send to player 2

            Player p1 = handler(in1, out1);
            Player p2 = handler(in2, out2);

            while (true) {
                out1.println("MENU");
                out2.println("MENU");

                String choice = in1.readLine(); // receives client 1 response from ([1] play [2] log out)menu
                String p2ready = in2.readLine(); // receives client 2 response play or quit

                if (p2ready.equalsIgnoreCase("quit")) {
                    out1.println("Opponent quit. Closing...");
                    return;
                }
                out2.println("Action: " + choice);

                if (choice.equals("1")) {
                    GameSession session = new GameSession(p1, p2);

                    // sign
                    out1.println("GAME STARTS NOW");
                    out2.println("GAME STARTS NOW");

                    // send both users their opponents
                    out1.println(p1.getUsername() + " VS " + p2.getUsername());
                    out2.println(p1.getUsername() + " VS " + p2.getUsername());

                    // 10 rounds
                    for (int i = 1; i <= 10; i++) {
                        out1.println("Round: " + i + "/10");
                        out2.println("Round: " + i + "/10");

                        String p1move = in1.readLine();
                        String p2move = in2.readLine();
                        session.setP1move(GameService.getMove(p1move));
                        session.setP2move(GameService.getMove(p2move));

                        String roundResult = session.playRound();
                        out1.println(roundResult);
                        out2.println(roundResult);
                        out1.println("END");
                        out2.println("END");
                    }
                    // the game over signal, without this the client's while loop would never exit
                    out1.println("GAME_OVER");
                    out2.println("GAME_OVER");

                    // shows the winner after 10 rounds
                    out1.println(session.determineOverallWinner());
                    out2.println(session.determineOverallWinner());
                    UserService.saveToJson();

                    // show the leaderboard
                    String leading = UserService.getLeaderboardperMatch();
                    System.out.println(leading);
                    for (String line : leading.split("\n")) {
                        out1.println(line);
                        out2.println(line);
                    }
                    out1.println("END");
                    out2.println("END");
                } else {
                    out1.println("LOGOUT");
                    out2.println("LOGOUT");
                    return;
                }

            }

        } catch (IOException e) {
            System.out.println("Disconnected...");
        }
    }

    public static Player handler(BufferedReader in, PrintWriter out) throws IOException {
        Player p = null;
        while (p == null) {
            String menu1Choice = in.readLine(); // log in or sign up menu
            String username = in.readLine(); // receives the input of the clients
            String password = in.readLine(); // receives the input of the clients

            String response = "";
            if (menu1Choice.equals("1")) {
                // 1 for logging in
                response = UserService.login(username, password);
            } else {
                // 2 for signing up
                response = UserService.signUp(username, password);
            }
            // send the result to the client so that the player will see it
            out.println(response);

            // check if you logged in or signed up succesfully and proceed to assigning the
            // player object
            if (response.contains("Welcome") || response.contains("successfully")) {
                p = GameService.findUsername(username);
            }
        }
        return p;
    }
}