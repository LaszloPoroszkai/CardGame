package com.codecool.api;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cardsInHand = new ArrayList<>();
    private GetRandom random = new GetRandom();

    public List<Card> getCardsInHand(){
        return cardsInHand;
    }

    public Card play(int index){
        return cardsInHand.get(index);
    }

    public void discard(){
        Card tmpCard = random.getRandomCard(cardsInHand);
        cardsInHand.remove(tmpCard);
    }
    public Card getRandomCard(){
        return random.getRandomCard(cardsInHand);
    }

}
