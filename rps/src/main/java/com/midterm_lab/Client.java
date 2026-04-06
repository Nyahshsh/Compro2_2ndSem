package com.midterm_lab;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String server = "localhost";
        int port = 7777;
        try (Socket socket = new Socket(server, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner input = new Scanner(System.in)) {

            // Identify Player
            String identity = in.readLine();
            System.out.println(identity);
            boolean isPlayer1 = identity.contains("PLAYER 1");

            // Log in or Sign up loop
            while (true) {
                System.out.println("""
                        ===================================
                                  WELCOME TO NYAH'S 
                            ROCK - PAPER - SCISSORS GAME
                        ===================================
                                 [1] Log in
                                 [2] Sign up
                        ===================================""");
                String choice;
                while (true) {
                    System.out.print("Choice: ");
                    choice = input.nextLine().trim();
                    if (choice.equals("1") || choice.equals("2")) {
                        break;
                    }
                    System.out.println("Invalid input! Please enter 1 or 2 only.");
                }
                out.println(choice);

                System.out.print("Username: ");
                out.println(input.nextLine());
                System.out.print("Password: ");
                out.println(input.nextLine());

                String response = in.readLine();
                System.out.println(response);

                if (response.contains("Welcome") || response.contains("successfully"))
                    break;
            }

            String actionChoice = "";
            while (true) {
                if (isPlayer1) {
                    in.readLine(); // Catch "MENU" signal
                    System.out.println("""

                            =======================================
                                            MAIN MENU
                            =======================================
                                        [1] Play Game
                                        [2] Log out
                            =======================================""");
                    actionChoice = "";
                    while (true) {
                        System.out.print("Choice: ");
                        actionChoice = input.nextLine().trim();
                        if (actionChoice.equals("1") || actionChoice.equals("2")) {
                            break;
                        }
                        System.out.println("Invalid input! Please enter 1 or 2 only.");
                    }
                    out.println(actionChoice);

                } else {
                    in.readLine(); // Catch "MENU" signal
                    String p2ready;
                    while (true) {
                        System.out.print("\nWaiting for Player 1... Type 'play' to start or 'quit' to leave: ");
                        p2ready = input.nextLine().trim();
                        if (p2ready.equalsIgnoreCase("play") || p2ready.equalsIgnoreCase("quit")) {
                            break;
                        }
                        System.out.println("Invalid input! Please type 'play' or 'quit' only.");
                    }
                    out.println(p2ready);

                    // Read Player 1 action from server
                    String actionLine = in.readLine();
                    if (actionLine != null && actionLine.contains(":")) {
                        actionChoice = actionLine.split(":")[1].trim();
                    }
                }

                if ("1".equals(actionChoice)) {
                    System.out.println("\n========================================");
                    System.out.println("                " + in.readLine()); // "GAME STARTS NOW"
                    System.out.println("              " + in.readLine()); // "p1 VS p2"
                    System.out.println("========================================");

                    String roundHeader;
                    while ((roundHeader = in.readLine()) != null && !roundHeader.equals("GAME_OVER")) {
                        System.out.println("\n" + roundHeader); // "Round X/10"
                        System.out.println("=========== CHOOSE YOUR PICK ===========");
                        System.out.println("  [0] Rock   [1] Paper   [2] Scissors");

                        String move;
                        while (true) {
                            System.out.print("Your move: ");
                            move = input.nextLine().trim();
                            if (move.equals("0") || move.equals("1") || move.equals("2"))
                                break;
                            System.out.println("Invalid input! Please enter 0, 1, or 2 only.");
                        }
                        out.println(move);

                        String picks = in.readLine(); //"Player 1 picked Rock | Player 2 picked Paper"
                        String winner = in.readLine(); //"Result: Player 2 wins this round"
                        System.out.println(picks);
                        System.out.println(winner);
                        in.readLine(); // catch "END" SIGNAL
                    }

                    System.out.println("\nGAME OVER...");
                    System.out.println(in.readLine()); // Overall winner

                    // DISPLAY LEADERBOARD
                    System.out.println("\n>>>>>>>>>>>>> LEADERBOARD <<<<<<<<<<<<<");
                    String line;
                    while ((line = in.readLine()) != null && !line.equals("END")) {
                        System.out.println(line);
                    }
                    System.out.println("=======================================");

                } else {
                    System.out.println("\nLogging out...");
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Disconnected: " + e.getMessage());
        }
    }
}