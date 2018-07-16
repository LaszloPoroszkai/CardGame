package com.codecool.gui;

import com.codecool.network.Host;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NetworkController {
    private Parent root;
    private Host server;
    private String message;
    @FXML
    AnchorPane ap;
    @FXML
    ImageView background;
    @FXML
    Button newServer;
    @FXML
    Button joinServer;
    @FXML
    TextArea serverInfo;
    @FXML
    TextField serverAddress;
    @FXML
    Button back;

    @FXML
    private void createServer() {
        if (server == null) {
            server = new Host();
        } else {
            message = "Cannot do that, there is already a server running";
        }
        message = server.getMessage();
        serverInfo.setText(message);
    }

    @FXML
    private void joinServer() {

    }

    @FXML
    private void backToMain() throws Exception {
        root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setScene(new Scene(root,1280, 720));
    }
}