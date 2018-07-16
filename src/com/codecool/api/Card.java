package com.codecool.api;

import java.io.Serializable;

public class Card implements Serializable{
    private String name;
    private int military;
    private int intrique;
    private int fame;
    private boolean state;
    private String link;

    public Card(String name, int military, int intrique, int fame, String link) {
        this.name = name;
        this.military = military;
        this.intrique = intrique;
        this.fame = fame;
        this.state = false;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public int getMilitary() {
        return military;
    }

    public int getIntrique() {
        return intrique;
    }

    public int getFame() {
        return fame;
    }

    public boolean isState() {
        return state;
    }

    public void setState(){
        state=!state;
    }

    public String getUrl() { return link; }

    @Override
    public String toString() {
        return name + "{m:" + military + ", i:" + intrique + ", f:" + fame + "} " + (state ? "Active" : "Tired");
    }
}
