package com.codecool.gui;

import com.codecool.api.CardReader;
import com.codecool.api.Player;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;


public class MainController {

    private Parent root;
    @FXML
    AnchorPane ap;
    @FXML
    Label startButton;
    @FXML
    Label exitButton;
    @FXML
    Label optionsButton;
    @FXML
    Label networkButton;


    //Choosing exit option
    public void exit() {
        System.exit(0);
    }

    //Choosing Options option
    public void enterOptions()throws Exception{
        root = FXMLLoader.load(getClass().getResource("optionsScreen.fxml"));
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setScene(new Scene(root,1280, 720));
    }


    //Choosing New Game*/
    public void startGame() throws Exception{
        AudioClip audio = new AudioClip(this.getClass().getResource("../../../sound/gong.mp3").toExternalForm());
        audio.play();
        Thread.sleep(3000);
        root = FXMLLoader.load(getClass().getResource("playerNameScreen.fxml"));
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setScene(new Scene(root,1280, 720));
    }


    public void networkOptions() throws Exception{
        root = FXMLLoader.load(getClass().getResource("networkScreen.fxml"));
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setScene(new Scene(root,1280, 720));
    }


    //Button Hover actions
    public void startButtonHover(){
        AudioClip audio = new AudioClip(this.getClass().getResource("../../../sound/hoverSound.wav").toExternalForm());
        audio.play();
        startButton.setScaleX(1.2);
        startButton.setScaleY(1.2);
    }

    public void startButtonHoverOff(){
        startButton.setScaleX(1);
        startButton.setScaleY(1);
    }

    public void exitButtonHover(){
        AudioClip audio = new AudioClip(this.getClass().getResource("../../../sound/hoverSound.wav").toExternalForm());
        audio.play();
        exitButton.setScaleX(1.2);
        exitButton.setScaleY(1.2);
    }

    public void exitButtonHoverOff(){
        exitButton.setScaleX(1);
        exitButton.setScaleY(1);
    }

    public void optionsButtonHover(){
        AudioClip audio = new AudioClip(this.getClass().getResource("../../../sound/hoverSound.wav").toExternalForm());
        audio.play();
        optionsButton.setScaleX(1.2);
        optionsButton.setScaleY(1.2);
    }

    public void optionsButtonHoverOff(){
        optionsButton.setScaleX(1);
        optionsButton.setScaleY(1);
    }

    public void networkButtonHover(){
        AudioClip audio = new AudioClip(this.getClass().getResource("../../../sound/hoverSound.wav").toExternalForm());
        audio.play();
        networkButton.setScaleX(1.2);
        networkButton.setScaleY(1.2);
    }

    public void networkButtonHoverOff(){
        networkButton.setScaleX(1);
        networkButton.setScaleY(1);
    }

}
