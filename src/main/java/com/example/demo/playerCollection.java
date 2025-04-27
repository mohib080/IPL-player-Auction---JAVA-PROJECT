package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class playerCollection {
    List<player> playerList = new ArrayList<>();
    private static final String INPUT_FILE_NAME ="src/main/java/com/example/demo/players";

    public void collectPlayers() {
        System.out.println("Loading players from file: " + INPUT_FILE_NAME);
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    String country = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    double height = Double.parseDouble(parts[3]);
                    String club = parts[4];
                    String position = parts[5];
                    int jerseyNumber = (!parts[6].isEmpty()) ? Integer.parseInt(parts[6]) : 0;
                    int weeklySalary = Integer.parseInt(parts[7]);

                    player p = new player(name, country, age, height, club, position, jerseyNumber, weeklySalary);
                    playerList.add(p);
                } catch (Exception e) {
                    System.err.println("Error parsing player data: " + line);
                    e.printStackTrace();
                }
            }
            System.out.println("Players loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error reading player data file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    void addPlayer(player obj) {
        for (player p : playerList) {
            if (p.getName().equalsIgnoreCase(obj.getName())) {
                System.out.println("Player With this name already exists");
                return;
            }
        }
        playerList.add(obj);
        System.out.println("Player Added Successfully");
    }

    player searchByName(String name) {
        for (player p : playerList) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    List<player> searchByCountryClub(String country, String club) {
        List<player> players = new ArrayList<>();
        if (club.equalsIgnoreCase("ANY")) {
            for (player p : playerList) {
                if (p.getCountry().equalsIgnoreCase(country)) {
                    players.add(p);
                }
            }
        } else {
            for (player p : playerList) {
                if (p.getCountry().equalsIgnoreCase(country) && p.getclub().equalsIgnoreCase(club)) {
                    players.add(p);
                }
            }
        }

        return players;
    }
    List<player> searchByClub(String club) {
        List<player> players = new ArrayList<>();
            for (player p : playerList) {
                if (p.getclub().equalsIgnoreCase(club) ) {
                    players.add(p);
                }
            }
        return players;
    }


    List<player> searchByPosition(String pos) {
        List<player>posList = new ArrayList<>();
        for (player p : playerList) {
            if (p.getPosition().equalsIgnoreCase(pos)) {
                posList.add(p);
            }
        }

        return posList;
    }

    List<player> searchBySalaryRange(int min, int max) {
        List<player> players = new ArrayList<>();
        for (player p : playerList) {
            if (p.getWeeklySalary() >= min && p.getWeeklySalary() <= max) {
                players.add(p);
            }
        }

        return players;
    }

    Map<String, Integer> CountryWisePlayerCount() {

        Map<String, Integer> countryCounts = new HashMap<>();

        for (player p : playerList) {
            String country = p.getCountry();
            countryCounts.put(country, countryCounts.getOrDefault(country, 0) + 1);
        }

        return countryCounts;
    }

    List<player> searchByMaxSalary(String clubName) {
        int maxSalary = Integer.MIN_VALUE;
        List<player> maxEarningPlayers = new ArrayList<>();

        for (player p : playerList) {
            if (p.getclub().equalsIgnoreCase(clubName)) {
                if (p.getWeeklySalary() > maxSalary) {
                    maxSalary = p.getWeeklySalary();
                    maxEarningPlayers.clear();
                    maxEarningPlayers.add(p);
                } else if (p.getWeeklySalary() == maxSalary) {
                    maxEarningPlayers.add(p);
                }
            }
        }

        return maxEarningPlayers;
    }

    List<player> searchByMaxAge(String clubName) {
        int maxAge = Integer.MIN_VALUE;
        List<player> maxAgePlayers = new ArrayList<>();

        for (player p : playerList) {
            if (p.getclub().equalsIgnoreCase(clubName)) {
                if (p.getAge() > maxAge) {
                    maxAge = p.getAge();
                    maxAgePlayers.clear();
                    maxAgePlayers.add(p);
                } else if (p.getAge() == maxAge) {
                    maxAgePlayers.add(p);
                }
            }
        }
        return maxAgePlayers;

    }

    List<player> searchByMaxHeight(String clubName) {
        double maxHeight = Double.MIN_VALUE;
        List<player> maxHeightPlayers = new ArrayList<>();

        for (player p : playerList) {
            if (p.getclub().equalsIgnoreCase(clubName)) {
                if (p.getHeight() > maxHeight) {
                    maxHeight = p.getHeight();
                    maxHeightPlayers.clear();
                    maxHeightPlayers.add(p);
                } else if (p.getHeight() == maxHeight) {
                    maxHeightPlayers.add(p);
                }
            }
        }
        return maxHeightPlayers;

    }

    long calculateTotalYearlySalary(String clubName) {
        long totalSalary = 0;

        for (player p : playerList) {
            if (p.getclub().equalsIgnoreCase(clubName)) {
                totalSalary += p.getWeeklySalary() * 52;
            }
        }

        return totalSalary;
    }

}
