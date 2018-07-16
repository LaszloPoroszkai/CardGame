package com.codecool.game;

import com.codecool.gui.GuiMain;
import com.codecool.network.Client;
import com.codecool.network.Host;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Host server = null;
        Client client = null;

        System.out.println("Would you like to play on Graphical Interface? (y/any other button)");
        String choice = sc.nextLine().toLowerCase();

        if (choice.equals("y")) {
            GuiMain gui = new GuiMain();
            gui.guimain();
        } else {
            // create new Game object. This is where most of the game will happen actually.
            Game newGame = new Game();


            // Display main menu and request basic input data (player names).
            // After that Game is called passing two player names
            while (true) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                int i = 1;
                int select;
                System.out.println("Welcome to Medieval Wars!\nPlease choose from the menu:\n");

                for (MainMenu menu : MainMenu.values()) {
                    System.out.println(Integer.toString(i) + " " + menu.getValue());
                    i++;
                }

                try {
                    select = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    sc.nextLine(); //message was not visible, without sc.nextline it goes into a loop ???
                    continue;
                }

                if (select == 1) {
                    System.out.println("Please provide name of the first player:");
                    String name1 = sc.nextLine();
                    System.out.println("Please provide name of the second player:");
                    String name2 = sc.nextLine();
                    newGame.startGame(name1, name2);
                } else if (select == 2) {
                    if (server == null) {
                        server = new Host();
                    } else {
                        System.out.println("Cannot do that, there is already a server running");
                    }
                } else if (select == 3) {
                    if (client == null) {
                        System.out.println("Please enter the ip address of the host: ");
                        String ip = sc.nextLine();
                        client = new Client(ip);
                        client.run();
                    } else {
                        System.err.println("No available host found");
                    }
                } else if (select == 4) {
                    System.exit(0);
                } else {
                    System.out.println("Invalid selection. Please hit enter and try again!");
                    sc.nextLine();
                }
            }
        }
    }
}