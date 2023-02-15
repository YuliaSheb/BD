package com.example.bd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.bd.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private TextField name;

    @FXML
    private TextField patronomic;

    @FXML
    private TextField surname;

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        add.setOnAction(event -> {
            databaseHandler.CreateBasket();
            databaseHandler.AddCustomer(name.getText(),surname.getText(),patronomic.getText());
            add.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(CustomerController.class.getResource("main-view.fxml"));
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
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

}
