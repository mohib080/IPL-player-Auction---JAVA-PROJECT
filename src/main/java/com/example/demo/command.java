package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class command {

    public void SearchByNameHandler(Scanner scn, playerCollection collection) {
        int subOption = 0;
        while (subOption != 6) {
            System.out.println("Player Searching Options:");
            System.out.println("(1) By Player name");
            System.out.println("(2) By Club and Country");
            System.out.println("(3) By position");
            System.out.println("(4) By salary range");
            System.out.println("(5) Country-wise player count");
            System.out.println("(6) Back to Main Menu");
            System.out.println("Choose the criteria you want to search with");
            while (!scn.hasNextInt()) {
                System.out.println("Invalid option! please choose between (1-6)");
                scn.next();
            }
            subOption = scn.nextInt();
            scn.nextLine();

            switch (subOption) {
                case 1:
                    System.out.println("Enter the name of the player:");
                    String name = scn.nextLine();
                    player p = collection.searchByName(name);
                    if (p == null)
                        System.out.println("No such player with this name");
                    else
                        System.out.println(p);
                    break;
                case 2:
                    System.out.println("Enter Country Name:");
                    String country = scn.nextLine();
                    System.out.println("Enter Club Name:");
                    String club = scn.nextLine();
                    List<player> players = collection.searchByCountryClub(country, club);
                    if (players.isEmpty())
                        System.out.println("No such player with this country and club");
                    else {
                        for (player p1 : players) {
                            System.out.println(p1);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter player position:");
                    String pos = scn.nextLine();
                    List<player>players1=collection.searchByPosition(pos);
                    if (players1 == null)
                        System.out.println("No such player with this position");
                    else
                        System.out.println(players1);
                    break;
                case 4:
                    System.out.println("Enter Minimum Salary:");
                    int min = scn.nextInt();
                    scn.nextLine();
                    System.out.println("Enter Maximum Salary:");
                    int max = scn.nextInt();
                    scn.nextLine();
                    List<player> players2 = collection.searchBySalaryRange(min, max);
                    if (players2.isEmpty())
                        System.out.println("No such player with this weekly salary range");
                    else {
                        for (player p2 : players2) {
                            System.out.println(p2);
                        }
                    }
                    break;
                case 5:
                    Map<String, Integer> countryCounts = collection.CountryWisePlayerCount();
                    System.out.println("Number of Players from each country:");
                    System.out.println("----------------------------------------");

                    countryCounts.forEach((countryName, count) -> System.out.println(countryName + ": " + count));
                    break;
                case 6:
                    System.out.println("Returning to Main Menu");
                    System.lineSeparator();
                    break;
                default:
                    System.out.println("Invalid option! Please choose between (1-6)");
            }
        }
    }

    public void searchByCountryClubHandler(Scanner scn, playerCollection collection) {
        int subOption2 = 0;
        while (subOption2 != 5) {
            System.out.println("Club Searching Options:");
            System.out.println("(1) Player(s) with the maximum salary of a club");
            System.out.println("(2) Player(s) with the maximum age of a club");
            System.out.println("(3) Player(s) with the maximum height of a club");
            System.out.println("(4) Total Yearly salary of a club");
            System.out.println("(5) Back to Main Menu");
            System.out.println("Choose any one of them");

            while (!scn.hasNextInt()) {
                System.out.println("Invalid option! Please choose between (1-5)");
                scn.next();
            }
            subOption2 = scn.nextInt();
            scn.nextLine();

            switch (subOption2) {
                case 1:
                    System.out.println("Enter Club Name:");
                    String clubName = scn.nextLine();
                    List<player> maxEarning = collection.searchByMaxSalary(clubName);
                    if (maxEarning.isEmpty())
                        System.out.println("No such club with this name");
                    else {
                        for (player p1 : maxEarning) {
                            System.out.println(p1);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter Club Name:");
                    clubName = scn.nextLine();
                    List<player> maxAge = collection.searchByMaxAge(clubName);
                    if (maxAge.isEmpty())
                        System.out.println("No such club with this name");
                    else {
                        for (player p2 : maxAge) {
                            System.out.println(p2);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter Club Name:");
                    clubName = scn.nextLine();
                    List<player> maxHeight = collection.searchByMaxHeight(clubName);
                    if (maxHeight.isEmpty())
                        System.out.println("No such club with this name");
                    else {
                        for (player p1 : maxHeight) {
                            System.out.println(p1);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter Club Name:");
                    clubName = scn.nextLine();
                    long n = 0;
                    n = collection.calculateTotalYearlySalary(clubName);
                    if (n == 0)
                        System.out.println("No such club with this name");
                    else {
                        System.out.println("Total weekly salary for the club: " + n);
                    }
                    break;
                case 5:
                    System.out.println("Returning to Main Menu");
                    break;
                default:
                    System.out.println("Invalid option! Please choose between (1-5)");
            }
        }
    }

    public void AddPlayerHandler(Scanner scn, playerCollection collection) {
        System.out.println("Enter player details:");
        System.out.print("Name: ");
        String name = scn.nextLine();
        System.out.print("Country: ");
        String country = scn.nextLine();
        System.out.print("Age: ");
        int age = scn.nextInt();
        scn.nextLine();
        System.out.print("Height: ");
        double height = scn.nextDouble();
        scn.nextLine();
        System.out.print("Club: ");
        String club = scn.nextLine();
        System.out.print("Position: ");
        String position = scn.nextLine();
        int jerseyNumber = 0;
        System.out.print("Jersey Number(optional): ");
        String jerseyInput = scn.nextLine();
        if (!jerseyInput.isEmpty()) {
            jerseyNumber = Integer.parseInt(jerseyInput);
        }
        System.out.print("Weekly Salary: ");
        int weeklySalary = scn.nextInt();
        scn.nextLine();

        player newPlayer = new player(name, country, age, height, club, position, jerseyNumber, weeklySalary);

        collection.addPlayer(newPlayer);

    }
}
