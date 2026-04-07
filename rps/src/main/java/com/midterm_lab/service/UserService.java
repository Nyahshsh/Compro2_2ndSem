package com.midterm_lab.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.midterm_lab.model.Player;

public class UserService {

    public static ArrayList<Player> players = new ArrayList<>();

    // The path to the file where player data is stored
    public static final String DATA_FILE = "src/data/Players.json";

    // Code for Log in
    public static String login(String username, String password) {
        for (Player player : players) {
            if (player.getUsername().equals(username) && player.getPassword().equals(password)) {
                return "Welcome back, " + username + "!";
            }
        }
        return "User not found!";
    }

    // Code for Sign up
    public static String signUp(String username, String password) {
      
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                return "Username already taken!";
            }
        }

        players.add(new Player(username, password, 0));
        saveToJson();
        return "Account successfully created! Welcome, " + username + "!";
    }

    // Leaderboard
    public static String getLeaderboardByWinRate() {
        // Sort players 
        players.sort((a, b) -> Double.compare(b.getWins(), a.getWins()));

        // Build the table text line by line
        StringBuilder table = new StringBuilder();

        // Add the header row
        table.append(String.format("%-15s %-10s %-10s %-10s", "Username", "Wins", "Played", "Win Rate"));
        table.append("\n---------------------------------------------------");

        // Add one row per player
        for (Player player : players) {
            int played = player.getGamesPlayed();

            // Calculate win rate as a percentage. 
            double winRate = 0.0;
            if (played > 0) {
                winRate = (player.getWins() * 100.0) / played;
            }

            table.append(String.format("\n%-15s %-10d %-10d %-10.2f%%",
                    player.getUsername(), player.getWins(), played, winRate));
        }

        return table.toString();
    }

    // Save File
    public static void saveToJson() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            gson.toJson(players, writer);
            System.out.println("Player data saved!");
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    // File Load
    public static void loadFromJson() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(DATA_FILE)) {

            Type listType = new TypeToken<ArrayList<Player>>() {}.getType();
            ArrayList<Player> saved = gson.fromJson(reader, listType);

            if (saved != null) {
                players = saved;
            }
        } catch (IOException e) {
            System.out.println("No saved data found. Starting fresh.");
        }
    }
}