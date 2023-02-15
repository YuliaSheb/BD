package com.example.bd;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DessertController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Profile;

    @FXML
    private Button cartButton;

    @FXML
    private Button add;

    @FXML
    private TextField Id;

    @FXML
    private TextField Login;

    @FXML
    private Text count;

    @FXML
    private TableView<Dessert> tablee;

    @FXML
    private TableColumn<Dessert, Integer> IdColumn;

    @FXML
    private TableColumn<Dessert, String> nameColumn;

    @FXML
    private TableColumn<Dessert, String> typeColumn;

    double Price = Math.round(Math.random()*1000-1);

    String query = null;
    PreparedStatement preparedStatement = null;
    Dessert dessert = null;
    ObservableList<Dessert> list_D;

    @FXML
    void initialize() {
        IdColumn.setCellValueFactory(new PropertyValueFactory<Dessert, Integer>("Id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Dessert, String>("Name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Dessert, String>("Type"));
        DatabaseHandler databaseHandler = new DatabaseHandler();
        list_D = databaseHandler.getDessert();
        tablee.setItems(list_D);
        Profile.setOnAction(event -> {
            Profile.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(DessertController.class.getResource("profile.fxml"));
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
        cartButton.setOnAction(event -> {
            cartButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(DessertController.class.getResource("basket.fxml"));
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
        add.setOnAction(event -> {
            databaseHandler.AddDesForOrd(2,"",Price, Integer.valueOf(Id.getText()),Login.getText());
        });
    }

}
