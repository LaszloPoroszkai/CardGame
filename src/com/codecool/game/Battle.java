package com.codecool.game;

import com.codecool.api.InvalidOptionException;
import com.codecool.api.NoUnitAvailableException;
import com.codecool.api.Player;
import com.codecool.api.ShowBoardState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Battle {
    private Scanner sc = new Scanner(System.in);
    private ShowBoardState stage = new ShowBoardState();

    public boolean resolveBattle(Player active, Player opponent, String choice) throws InvalidOptionException {
        int attackPower = 0;
        int defenderPower = 0;
        List<Integer> attackers = new ArrayList<>();
        List<Integer> defenders = new ArrayList<>();

        if (choice.equals("m")) {
            choice = "military";
        }else if (choice.equals("i")) {
            choice = "intrique";
        }else if (choice.equals("f")) {
            choice = "fame";
        }

        //first attacker needs to choose with which cards to attack. Any number of attackers can be
        //selected but can not select the same character twice. Also trying to catch exceptions.
        //tired characters can't attack

        while (true) {

            //Information printed on screen:
            stage.showBoard(active, opponent);
            System.out.println("\nCharacters you have already sent to attack:");
            for (int i = 0; i < attackers.size(); i++) {
                System.out.print("|" + active.getBoard().getOnBoard().get(attackers.get(i)).getName() + "|\t");
            }
            System.out.println("\n\nYour total strength is: " + Integer.toString(attackPower));
            System.out.println("Please select an attacker, X to cancel or hit Enter to finish selection");
            String selection = sc.nextLine().toLowerCase();

            //selection gets evaluated:
            if (selection.length() > 1) {
                throw new InvalidOptionException("There is no such option!");
            } else if (selection.length() == 0) {
                break;
            } else if (selection.equals("x")) {
                attackers.clear();
                attackPower = 0;
            } else if ("123456789".contains(selection)) {
                Integer selected = Integer.parseInt(selection) - 1;
                if (attackers.contains(selected)) {
                    throw new InvalidOptionException("Already declared as attacker!");
                } else if (!active.getBoard().getOnBoard().get(selected).isState()) {
                    throw new InvalidOptionException("This unit is too tired to fight!");
                } else {
                    attackers.add(selected);
                    if (choice.equals("military")) {
                        attackPower += active.getBoard().getOnBoard().get(selected).getMilitary();
                    } else if (choice.equals("intrique")) {
                        attackPower += active.getBoard().getOnBoard().get(selected).getIntrique();
                    } else {
                        attackPower += active.getBoard().getOnBoard().get(selected).getFame();
                    }
                }
            }
        }


        //after attackers are declared opponent can choose defenders. Maybe this option shouldn't
        //pop up if defender has no cards on the board, but I think it is good to show him that he is
        //attacked. Tired characters can't defend.

        if (attackers.size() > 0) {
            while (true) {

                //This is just printing information on the screen
                stage.showBoard(opponent, active);
                System.out.println("\n" + active.getName() + " has initiated a " + choice + " battle against you!");
                System.out.println("His attackers are: ");
                for (int i = 0; i < attackers.size(); i++) {
                    System.out.print("|" + active.getBoard().getOnBoard().get(attackers.get(i)).getName() + "|\t");
                }
                System.out.println("His total strength is: " + Integer.toString(attackPower));
                System.out.println("\nPlease select a defender, X to cancel or hit Enter to finish selection");
                System.out.println("Your have declared following defenders so far: ");
                for (int i = 0; i < defenders.size(); i++) {
                    System.out.print("|" + opponent.getBoard().getOnBoard().get(attackers.get(i)).getName() + "|\t");
                }
                System.out.println("\n\nYour total strength is: " + Integer.toString(defenderPower));
                String selection = sc.nextLine().toLowerCase();

                //this is where stuff happens
                if (selection.length() > 1) {
                    throw new InvalidOptionException("There is no such option!");
                } else if (selection.length() == 0) {
                    break;
                } else if (selection.equals("x")) {
                    defenders.clear();
                    defenderPower = 0;
                } else if ("123456789".contains(selection)) {
                    Integer selected = Integer.parseInt(selection) - 1;
                    if (defenders.contains(selected)) {
                        throw new InvalidOptionException("Already declared as defender!");
                    } else if (!opponent.getBoard().getOnBoard().get(selected).isState()) {
                        throw new InvalidOptionException("This unit is too tired to fight! Hit enter to continue");
                    } else {
                        attackers.add(selected);
                        if (choice.equals("military")) {
                            defenderPower += opponent.getBoard().getOnBoard().get(selected).getMilitary();
                        } else if (choice.equals("intrique")) {
                            defenderPower += opponent.getBoard().getOnBoard().get(selected).getIntrique();
                        } else {
                            defenderPower += opponent.getBoard().getOnBoard().get(selected).getFame();
                        }
                    }
                }
            }
        }


        //after defenders are selected here we determine the outcome of the battle based on total powers.
        //if attacker wins, either 1 card from board, 2 cards from hand or 3 from deck will be discarded.
        //in case of tie or defenders win nothing happens.

        if (attackPower > defenderPower) {
            if (choice.equals("military")) {
                try {
                    opponent.getBoard().destroyCard();
                } catch (NoUnitAvailableException e) {
                    sc.nextLine();
                }
            } else if (choice.equals("intrique")) {
                for (int i = 0; i < 2; i++) {
                    opponent.getHand().discard();
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    if (opponent.getDeck().getCardList().size() == 0) {
                        return false;
                    } else {
                        opponent.getDeck().mill();
                    }
                }
            }


            //setting attacker's state to tired.
            for (Integer i : attackers) {
                active.getBoard().getOnBoard().get(i).setState();
            }
        }
        return true;
    }
}
