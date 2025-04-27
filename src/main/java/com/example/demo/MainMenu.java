package com.example.demo;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        playerCollection collection = new playerCollection();
        collection.collectPlayers();

        Scanner scn = new Scanner(System.in);
        command cmnd = new command();
        int option = 0;
        while (option != 4) {
            System.out.println("Main Menu:");
            System.out.println("(1) Search Players");
            System.out.println("(2) Search Clubs");
            System.out.println("(3) Add Player");
            System.out.println("(4) Exit System");

            System.out.println("Choose an option:");

            while (!scn.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                scn.next();
            }
            option = scn.nextInt();
            scn.nextLine();

            switch (option) {
                case 1:
                    cmnd.SearchByNameHandler(scn, collection);
                    break;
                case 2:
                    cmnd.searchByCountryClubHandler(scn, collection);
                    break;
                case 3:
                    cmnd.AddPlayerHandler(scn, collection);
                    break;
                case 4:
                    System.out.println("Exiting System.........");
                    break;
                default:
                    System.out.println("Invalid option!Please choose between (1-4)");
            }
        }
        scn.close();

    }
}