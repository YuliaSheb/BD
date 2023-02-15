package com.example.bd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registration;

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        registration.setOnAction(event -> {
            databaseHandler.SignUpUser(loginField.getText(),passwordField.getText());
            registration.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(SignUpController.class.getResource("customer-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Parent root = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.showAndWait();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("New User!");
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
