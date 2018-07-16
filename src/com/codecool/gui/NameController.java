package com.codecool.gui;

import com.codecool.api.CardReader;
import com.codecool.api.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.codecool.api.Player;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Random;

public class NameController {
    private GameState gm = GameState.getInstance();

    @FXML
    private AnchorPane ap;
    @FXML
    TextField playerOneName;
    @FXML
    TextField playerTwoName;
    @FXML
    ImageView confirmButton;
    @FXML
    ChoiceBox playerOneDeck;
    @FXML
    ChoiceBox playerTwoDeck;

    public void initialize() {
        File dir = new File("src");
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });
        ObservableList<String> availableChoices = FXCollections.observableArrayList();

        for(File deck : files) {
            availableChoices.add(deck.getName());
        }

        playerOneDeck.setItems(availableChoices);
        playerTwoDeck.setItems(availableChoices);

    }

    public void confirmButton()throws Exception{
        Random random = new Random();

        Player playerOne = new Player(playerOneName.getText());
        Player playerTwo = new Player(playerTwoName.getText());

        playerOne.setActive();

        gm.addToPlayers(playerOne);
        gm.addToPlayers(playerTwo);

        String path1 = "src/" + playerOneDeck.getSelectionModel().getSelectedItem();
        String path2 = "src/" + playerTwoDeck.getSelectionModel().getSelectedItem();

        CardReader cr = new CardReader();
        cr.loadDeck(playerOne, path1);
        cr.loadDeck(playerTwo, path2);
        playerOne.drawStartingHand();
        playerTwo.drawStartingHand();

        Parent root = FXMLLoader.load(getClass().getResource("battleScreen.fxml"));
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setScene(new Scene(root,1280,720));
    }


    public void confirmButtonHover(){
        AudioClip mApplause = new AudioClip(this.getClass().getResource("../../../sound/hoverSound.wav").toExternalForm());
        mApplause.play();
        confirmButton.setScaleX(1.1);
        confirmButton.setScaleY(1.1);

    }

    public void confirmButtonHoverOff(){
        confirmButton.setScaleX(1);
        confirmButton.setScaleY(1);
    }
}
