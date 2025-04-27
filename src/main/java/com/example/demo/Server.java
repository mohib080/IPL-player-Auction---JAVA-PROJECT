package com.example.demo;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 12345;
    private static playerCollection collection;

    public static void main(String[] args) {
        collection = new playerCollection();
        collection.collectPlayers();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running...");
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void sellPlayer(String clubName, String playerName) {
        player p = collection.searchByName(playerName);
        if (p != null && p.getclub().equalsIgnoreCase(clubName)) {
            p.setClub("NONE");
            System.out.println("Player " + playerName + " sold by " + clubName);
        } else {
            System.err.println("Error: Player '" + playerName + "' not found in club '" + clubName + "'.");
        }
    }

    private static synchronized void buyPlayer(String clubName, String playerName) {
        player p = collection.searchByName(playerName);
        if (p != null && p.getclub().equalsIgnoreCase("NONE")) {
            p.setClub(clubName);
            System.out.println("Player " + playerName + " bought by " + clubName);
        } else {
            System.err.println("Error: Player '" + playerName + "' is not available to buy.");
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String request;
                while ((request = in.readLine()) != null) {
                    String[] parts = request.split(":");
                    String action = parts[0];
                    String clubName = parts.length > 1 ? parts[1] : "";
                    String playerName = parts.length > 2 ? parts[2] : "";

                    if (action.equals("SELL")) {
                        sellPlayer(clubName, playerName);
                        out.println("Player " + playerName + " sold by " + clubName);
                    } else if (action.equals("BUY")) {
                        buyPlayer(clubName, playerName);
                        out.println("Player " + playerName + " bought by " + clubName);
                    }
                    else if (action.equals("ADD")) {
                        String newPlayerName = parts[1];
                        String newPlayerCountry = parts[2];
                        int newPlayerAge = Integer.parseInt(parts[3]);
                        double newPlayerHeight = Double.parseDouble(parts[4]);
                        String newPlayerClub = parts[5];
                        String newPlayerPosition = parts[6];
                        int newPlayerJersey = Integer.parseInt(parts[7]);
                        int newPlayerSalary = Integer.parseInt(parts[8]);

                        player newPlayer = new player(newPlayerName, newPlayerCountry, newPlayerAge, newPlayerHeight,
                                newPlayerClub, newPlayerPosition, newPlayerJersey, newPlayerSalary);

                        collection.addPlayer(newPlayer);
                        System.out.println("Player " + newPlayerName + " added to server's collection.");
                    }
                    else if (action.equals("GET_BUYABLE")) {
                        List<player> buyablePlayers = collection.searchByClub("NONE");
                        List<String> filteredPlayers = new ArrayList<>();
                        for (player p : buyablePlayers) {
                            if (!p.getclub().equalsIgnoreCase(clubName)) {
                                filteredPlayers.add(p.getName());
                            }
                        }
                        out.println(String.join(",", filteredPlayers));
                    } else if (action.equals("GET_PLAYERS")) {
                        List<player> clubPlayers = collection.searchByClub(clubName);
                        out.println(String.join(",", clubPlayers.stream().map(player::getName).toList()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}