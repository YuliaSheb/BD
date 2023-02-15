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

public class OrderConfectionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddNew;

    @FXML
    private Button AddNew1;

    @FXML
    private TableColumn<Order,String> StateColumn;

    @FXML
    private TextField UpId1;

    @FXML
    private TableColumn<Order,Integer> IdColumn;

    @FXML
    private Button Orders;

    @FXML
    private Button Profile;

    @FXML
    private TableColumn<Order,String> TypeColumn;

    @FXML
    private TextField UpId;

    @FXML
    private TableColumn<Order,String> nameColumn;

    @FXML
    private TableView<Order> tablee;

    ObservableList<Order> list_D;

    void show()
    {
        IdColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("Name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("Type"));
        StateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("StateOrder"));
        DatabaseHandler databaseHandler = new DatabaseHandler();
        list_D = databaseHandler.getOrderC();
        tablee.setItems(list_D);
    }

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        show();
        AddNew.setOnAction(event -> {
            databaseHandler.Ord(Integer.parseInt(UpId.getText()));
            show();
        });
        AddNew1.setOnAction(event -> {
            databaseHandler.DoneOrd(Integer.parseInt(UpId1.getText()));
            show();
        });
        Profile.setOnAction(event -> {
            Profile.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(OrderConfectionController.class.getResource("hello-view-con.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        Orders.setOnAction(event -> {
            Orders.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(OrderConfectionController.class.getResource("mainC-view.fxml"));
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
