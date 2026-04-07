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
    public static final String filename = "C:\\Users\\STUDENT\\Documents\\PRACODES-JAVA\\midterms\\src\\data\\Players.json";

    public static String login(String username, String password) {
        for (Player p : players) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
                return "Welcome back, " + username + "!";
            }
        }
        return "User not found!";
    }

    public static String signUp(String username, String password) {
        for (Player p : players) {
            if (p.getUsername().equals(username))
                return "Username already taken!";
        }
        Player newPlayer = new Player(username, password, 0);
        players.add(newPlayer);
        saveToJson();
        return "Account successfully created! \n\tWelcome, " + username;
    }

    public static String getLeaderboardByWinRate() {
        players.sort((p1, p2) -> Double.compare(p2.getWins(), p1.getWins()));

        StringBuilder result = new StringBuilder();
        result.append(String.format("%-15s %-10s %-10s %-10s\n", "Username", "Wins", "Played", "Win Rate"));
        result.append("---------------------------------------------------\n");

        for (Player p : players) {
            int gamesPlayed = p.GamesPlayed();
            double winRate = gamesPlayed > 0 ? (p.getWins() * 100.0 / gamesPlayed) : 0.0;

            result.append(String.format("%-15s %-10d %-10d %-10.2f%%\n",
                    p.getUsername(),
                    p.getWins(),
                    gamesPlayed, 
                    winRate));
        }
        return result.toString();
    }

    public static void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(players, writer);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving player data");
        }
    }

    public static void loadFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            Type playerListType = new TypeToken<ArrayList<Player>>() {
            }.getType();
            ArrayList<Player> loadedPlayers = gson.fromJson(reader, playerListType);
            if (loadedPlayers != null) {
                players = loadedPlayers;
            }
        } catch (IOException e) {
            System.out.println("Error loading player data: " + e.getMessage());
        }
    }
}