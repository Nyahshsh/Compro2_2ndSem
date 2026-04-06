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
                    if (choice.equals("1") || choice.equals("2"))
                        break;
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

            // Main Game Loop
            while (true) {
                String signal = in.readLine(); // "MENU"
                if (signal == null || signal.equals("LOGOUT"))
                    break;

                String myChoice = "";
                if (isPlayer1) {
                    System.out.println("""
                            =======================================
                                           MAIN MENU
                            =======================================
                                         [1] Play Game
                                         [2] Log out
                            =======================================""");
                    while (true) {
                        System.out.print("Choice: ");
                        myChoice = input.nextLine().trim();
                        if (myChoice.equals("1") || myChoice.equals("2"))
                            break;
                        System.out.println("Invalid input! Please enter 1 or 2 only.");
                    }
                    out.println(myChoice);
                } else {
                    System.out.println("Waiting for Player 1 to decide...");
                    String p1Status = in.readLine(); // "P1_CHOICE:X"
                    if (p1Status == null || p1Status.contains(":2")) {
                        System.out.println("Opponent is logging out.");
                        break;
                    }
                    System.out.println("\nPlayer 1 wants to play! [1] Accept [2] Logout");
                    while (true) {
                        System.out.print("Choice: ");
                        myChoice = input.nextLine().trim();
                        if (myChoice.equals("1") || myChoice.equals("2"))
                            break;
                        System.out.println("Invalid! Enter 1 to Accept or 2 to Logout.");
                    }
                    out.println(myChoice);
                }

                // If user chose logout, exit loop
                if (myChoice.equals("2"))
                    break;

                // See if game starts or opponent declined
                String serverResponse = in.readLine();
                if (serverResponse == null || serverResponse.equals("OPPONENT_LEFT")) {
                    System.out.println("The match was cancelled (Opponent declined).");
                    break;
                }

                System.out.println("\n========================================");
                System.out.println("             " + serverResponse); // "GAME STARTS NOW"
                System.out.println("            " + in.readLine()); // "p1 VS p2"
                System.out.println("========================================");

                String roundHeader;
                // Game rounds loop
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
                        System.out.println("Invalid! Enter 0, 1, or 2.");
                    }
                    out.println(move);

                    System.out.println(in.readLine()); // Picks result
                    System.out.println(in.readLine()); // Round winner
                }

                System.out.println("\nGAME OVER...");
                System.out.println(in.readLine()); // Overall winner

                // Display Leaderboard (Win Rate Type)
                System.out.println("\n------------ L E A D E R B O A R D ---------------");
                String lbLine;
                while ((lbLine = in.readLine()) != null && !lbLine.equals("LB_END")) {
                    System.out.println(lbLine);
                }
                System.out.println("-------------------------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Disconnected: " + e.getMessage());
        }
    }
}