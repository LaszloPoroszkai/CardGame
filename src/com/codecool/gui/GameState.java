package com.codecool.gui;

import com.codecool.api.Card;
import com.codecool.api.Player;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private List<Player> players = new ArrayList<>();
    private List<Card> attackers = new ArrayList<>();
    private List<Card> defenders = new ArrayList<>();
    private String phase="";

    private static GameState ourInstance = null;

    public static GameState getInstance() {
        if (ourInstance == null) {
            ourInstance = new GameState();
        }
        return ourInstance;
    }

    private GameState() {
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addToPlayers(Player player) {
        players.add(player);
    }

    public void addToAttackers(Card card){
        attackers.add(card);
    }

    public void removeFromAttackers(Card card){
        attackers.remove(card);
    }

    public List<Card> getAttackers() {
        return attackers;
    }

    public List<Card> getDefenders() {
        return defenders;
    }

    public void addToDefenders(Card card){
        defenders.add(card);
    }

    public void removeFromDefenders(Card card) { defenders.remove(card); }

}
