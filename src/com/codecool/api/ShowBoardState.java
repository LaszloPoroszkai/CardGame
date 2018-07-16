package com.codecool.api;

public class ShowBoardState {

    public void showBoard(Player activePlayer, Player opponent) {
        int maxBoardIndex = (activePlayer.getBoard().getOnBoard().size() > opponent.getBoard().getOnBoard().size() ? activePlayer.getBoard().getOnBoard().size() : opponent.getBoard().getOnBoard().size());

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.format("%-80s %s%n%n", activePlayer.getName(), opponent.getName());

        System.out.format("Cards in your deck: %-60d %s : %d%n%n", activePlayer.getDeck().getCardList().size(), "Cards in opponent's deck", opponent.getDeck().getCardList().size());

        System.out.format("%-80s %s %d%n", "Cards in your hand:", "Cards in opponent's hand: ", opponent.getHand().getCardsInHand().size());

        for (int i = 1; i < activePlayer.getHand().getCardsInHand().size()+1; i++) {
            System.out.format("%d)" + activePlayer.getHand().getCardsInHand().get(i-1).toString() + "%n", i);
        }

        System.out.println();
        System.out.format("%-80s %s%n", "Your Board:", "Opponent's Board:");

        for (int i = 1; i < maxBoardIndex+1; i++) {
            if (activePlayer.getBoard().getOnBoard().size() > i-1) {
                System.out.format("%d) %-80s",i ,activePlayer.getBoard().getOnBoard().get(i-1).toString());
            }else{
                System.out.format("%-80s", " ");
            }

            if (opponent.getBoard().getOnBoard().size() > i-1) {
                System.out.format("%d) %s",i ,opponent.getBoard().getOnBoard().get(i-1).toString());
            }
            System.out.println();
        }

        /*System.out.format("Opponent's hand: %d %15s %d", opponent.getHand().getCardsInHand().size(), "Opponent's deck:", opponent.getDeck().getCardList().size());
        System.out.println();

        for (int i=1; i<opponent.getBoard().getOnBoard().size()+1;i++) {
            Card card = opponent.getBoard().getOnBoard().get(i-1);
            System.out.format(i + "%5s [m:%d, i:%d, f:%d] ", card.getName(), card.getMilitary(), card.getIntrique(), card.getFame(), card.getName(), card.getMilitary(), card.getIntrique(), card.getFame());
            System.out.format(card.isState() ? "Active" : "Tired");
        }

        System.out.println();
        System.out.println();

        for (int i = 1; i < activePlayer.getBoard().getOnBoard().size()+1; i++) {
            Card card = activePlayer.getBoard().getOnBoard().get(i-1);
            System.out.format(i + "%5s%n [m:%d, i:%d, f:%d] ", card.getName(), card.getMilitary(), card.getIntrique(), card.getFame(), card.getName(), card.getMilitary(), card.getIntrique(), card.getFame());
            System.out.format(card.isState() ? "Active" : "Tired");
        }

        System.out.println();

        for (int i = 1; i < activePlayer.getHand().getCardsInHand().size()+1; i++) {
            Card card = activePlayer.getHand().getCardsInHand().get(i-1);
            System.out.format(i + ") %5s [m:%d, i:%d, f:%d] ", card.getName(), card.getMilitary(), card.getIntrique(), card.getFame(), card.getName(), card.getMilitary(), card.getIntrique(), card.getFame());
        }

        System.out.println();
        System.out.format("Cards in your deck: %d\n", activePlayer.getDeck().getCardList().size());
        System.out.println();*/
    }
}
