package com.codecool.api;
import java.util.List;
import java.util.Random;

public class GetRandom {

    public Card getRandomCard(List<Card> cards){
        Random rand = new Random();
        int index = rand.nextInt(cards.size());
        return cards.get(index);
    }
}
