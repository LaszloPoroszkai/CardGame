package com.codecool.game;

public enum MainMenu {
    PLAY("New game"),
    MULTIPLAYERHOST("Start a server for multiplaying"),
    MULTIPLAYERJOIN("Join an existing server"),
    EXIT("Exit");

    final private String value;

    MainMenu(String s) {
        value = s;
    }

    public String getValue() { return value; }
}
