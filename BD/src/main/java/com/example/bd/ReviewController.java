package com.example.bd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Dessert;

    @FXML
    private Button add;

    @FXML
    private TextField name1;

    @FXML
    private TextField name11;

    @FXML
    private TextArea Reviews;

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        add.setOnAction(event -> {
            databaseHandler.AddReview(name11.getText(),Reviews.getText(), Integer.valueOf(name1.getText()));
        });
        Dessert.setOnAction(event -> {
            Dessert.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(ReviewController.class.getResource("main-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

}
