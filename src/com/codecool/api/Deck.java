package com.codecool.api;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cardList = new ArrayList<>();
    private GetRandom random = new GetRandom();

    public Card getRandomCards(){
        return random.getRandomCard(cardList);
    }

    public void setCardList(Card card) {
        cardList.add(card);
    }

    public List<Card> getCardList(){
        return cardList;
    }

    public void mill() {
        Card tmpCard = random.getRandomCard(getCardList());
        getCardList().remove(tmpCard);
    }
}