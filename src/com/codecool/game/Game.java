package com.codecool.game;

import com.codecool.api.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game implements Serializable{
    private CardReader cr = new CardReader();
    private Random random = new Random();
    private List<Player> players = new ArrayList<>(); //to be able to check who is active
    private ShowBoardState stage = new ShowBoardState(); //this class will print board states
    private Scanner sc = new Scanner(System.in);
    private Battle battle = new Battle(); //I put Battle in another class as it is quite large

    // startGame will initialize the game. Setup decks, starting hand, create Player objects.
    // afterwards it calls runGame.

    public void startGame(String name1, String name2) {
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);

        players.add(player1);
        players.add(player2);

        cr.loadDeck(player1, "src/Cards.xml");
        cr.loadDeck(player2, "src/Cards.xml");

        if (random.nextBoolean()) {
            player1.setActive();
        } else {
            player2.setActive();
        }

        player1.drawStartingHand();
        player2.drawStartingHand();

        runGame();
    }

    // This is the heart of the game.
    // Game is split into phases. runGame will call those phases one after another in a loop
    // while one of the players wins.
    private void runGame() {
        while(true) {
            upkeep(getActivePlayer()); //setting the state of the cards on board to active
            recruit(getActivePlayer()); //playing new cards from your hand
            try {
                boolean checkGame = battle(getActivePlayer()); //initiating battles (2 at most)
                if (!checkGame) {
                    break;
                }
            }catch (InvalidOptionException e) {
                System.out.println("Hit enter and try again!");
            }
            boolean checkGame = draw(getActivePlayer()); //draw until you have 5 cards in hand
            if (!checkGame) {
                break;
            }
            endStep(getActivePlayer(), getOpponent()); //pass, switch players' state and start next turn
        }
        endGame();
    }

    //upkeep: if any card state on your board is tired, they will be activated

    private void upkeep(Player player) {
        for (int i = 0;i<player.getBoard().getOnBoard().size();i++) {
            if (!player.getBoard().getOnBoard().get(i).isState()) {
                player.getBoard().getOnBoard().get(i).setState();
            }
        }
    }


    //recruit: play at most 2 cards from your hand

    private void recruit(Player player) {
        for (int i=0; i < 2;i++) {
            stage.showBoard(player, getOpponent());
            System.out.println("Please choose a card to play or 0 to proceed to next phase");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice==0) {
                break;
            }else if (choice<=player.getHand().getCardsInHand().size() && choice > 0) {
                player.playFromHand(choice-1);
            }
        }
    }

    //initiate at most 2 battles. See more in Battle.java.
    //if opponent has no card left in deck after fame battle, he loses.

    private boolean battle(Player activePlayer) throws InvalidOptionException {
        int fightsStarted = 0;
        boolean checkGame = true;
        while (true) {
            stage.showBoard(activePlayer, getOpponent());
            System.out.println("\nDo you want to launch an attack?");
            System.out.println("(M)ilitary\t(I)ntrique\t(F)ame\t(N)o attack");
            String choice = sc.nextLine().toLowerCase();

            if (choice.length() > 1 || choice.length() == 0) {
                throw new InvalidOptionException("There is no such option!");
            }else if ("mifn".contains(choice)) {
                if (choice.equals("n")) {
                    break;
                }else {
                    try {
                        checkGame = battle.resolveBattle(activePlayer, getOpponent(), choice);
                        fightsStarted++;
                        if (!checkGame) {
                            break;
                        }
                    }catch (InvalidOptionException e) {
                        sc.nextLine();
                    }
                }
            }
            if (fightsStarted == 2) {
                break;
            }
        }
        return checkGame;
    }


    //draw to 5 cards. If not enough cards left in deck, you lose.

    private boolean draw(Player activePlayer) {
        boolean checkGame = true;
        stage.showBoard(activePlayer, getOpponent());
        while (activePlayer.getHand().getCardsInHand().size() < 5) {
            checkGame = activePlayer.draw();
        }
        return checkGame;
    }


    //pass turn, switch status of players.

    private void endStep(Player activePlayer, Player opponent) {
        stage.showBoard(activePlayer, opponent);
        System.out.println("Please hit Enter to pass");
        sc.nextLine();
        activePlayer.setActive();
        opponent.setActive();
    }


    //if any player wins, this will be called

    private void endGame(){
        Player winner = (getActivePlayer().getDeck().getCardList().size()==0 ? getOpponent() : getActivePlayer());
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.format("%s wins the game and will be crowned!%n", winner.getName());
        System.out.println("Hit enter to get back to the main menu.");
        sc.nextLine();
    }


    //below two methods are to find active and inactive player.
    private Player getActivePlayer() {
        Player activePlayer = null;
        for (Player player : players) {
            if (player.isActive()) {
                activePlayer = player;
            }
        }
        return activePlayer;
    }

    private Player getOpponent() {
        Player opponent = null;
        for (Player player : players) {
            if (!player.isActive()) {
                opponent = player;
            }
        }
        return opponent;
    }
}

