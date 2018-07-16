package com.codecool.gui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;

import java.awt.*;

public class OptionController {
    private Parent root;
    @FXML
    AnchorPane ap;
    @FXML
    ImageView background;

    public void goBackToMain()throws Exception {
        root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setScene(new Scene(root, 1280, 720));
    }

    public void setFullscreen(){
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setFullScreen(!stage.isFullScreen());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        background.setFitWidth(screenSize.getWidth());
        background.setFitHeight(screenSize.getHeight());
    }
}
