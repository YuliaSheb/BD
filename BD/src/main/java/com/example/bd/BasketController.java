package com.example.bd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;

public class BasketController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Dessert;

    @FXML
    private TableColumn<Basket,String> DessertColumn;

    @FXML
    private Button Exit;

    @FXML
    private TableColumn<Basket,Integer> OrderIdColumn;

    @FXML
    private TableColumn<Basket,Double> PriceColumn;

    @FXML
    private TableColumn<Basket,String> TypeColumn;

    @FXML
    private TableColumn<Basket,String> StateOrderColumn;

    @FXML
    private Button add;

    @FXML
    private TextField name1;

    @FXML
    private TableView<Basket> tablee;

    ObservableList<Basket> list_D;

    void show()
    {
        OrderIdColumn.setCellValueFactory(new PropertyValueFactory<Basket, Integer>("Id"));
        DessertColumn.setCellValueFactory(new PropertyValueFactory<Basket, String>("Name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Basket, String>("Type"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<Basket, Double>("Prices"));
        StateOrderColumn.setCellValueFactory(new PropertyValueFactory<Basket, String>("StateOrder"));
        DatabaseHandler databaseHandler = new DatabaseHandler();
        list_D = databaseHandler.getBasket();
        tablee.setItems(list_D);
    }

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        show();
        Dessert.setOnAction(event -> {
            Dessert.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(ProfileController.class.getResource("main-view.fxml"));
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
        Exit.setOnAction(event -> {
            Exit.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(ProfileController.class.getResource("hello-view.fxml"));
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
            databaseHandler.UpdateState(Integer.valueOf(name1.getText()));
            show();
        });
    }

}
