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

public class DessertCController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddNew;

    @FXML
    private TableColumn<DessertC,Integer> CarbColumn;

    @FXML
    private Button Delete;

    @FXML
    private TableColumn<DessertC,Integer> FatColumn;

    @FXML
    private TableColumn<DessertC,Integer> IdColumn;

    @FXML
    private TableColumn<DessertC,String> IngredientColumn;

    @FXML
    private Button Orders;

    @FXML
    private Button Profile;

    @FXML
    private TableColumn<DessertC,Integer> ProteinColumn;

    @FXML
    private TableColumn<DessertC,String> TypeColumn;

    @FXML
    private TextField UpName;

    @FXML
    private TextField UpId;

    @FXML
    private TextField UpCarbohydrate;

    @FXML
    private TextField UpFat;

    @FXML
    private TextField UpIngredient;

    @FXML
    private TextField UpProtein;

    @FXML
    private TextField UpType;

    @FXML
    private Button Update;

    @FXML
    private TableColumn<DessertC,String> nameColumn;

    @FXML
    private TableView<DessertC> tablee;

    ObservableList<DessertC> list_D;

    void show() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        IdColumn.setCellValueFactory(new PropertyValueFactory<DessertC, Integer>("Id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<DessertC, String>("Name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<DessertC, String>("Type"));
        IngredientColumn.setCellValueFactory(new PropertyValueFactory<DessertC, String>("Ingredient"));
        ProteinColumn.setCellValueFactory(new PropertyValueFactory<DessertC, Integer>("Protein"));
        FatColumn.setCellValueFactory(new PropertyValueFactory<DessertC, Integer>("Fat"));
        CarbColumn.setCellValueFactory(new PropertyValueFactory<DessertC, Integer>("Carb"));
        list_D = databaseHandler.getDessertC();
        tablee.setItems(list_D);
    }

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        show();
        AddNew.setOnAction(event -> {
            databaseHandler.AddNewDessertTest(UpName.getText(), UpType.getText());
            //databaseHandler.AddNewIngredient(UpIngredient.getText(),Integer.parseInt(UpProtein.getText()),Integer.parseInt(UpFat.getText()),Integer.parseInt(UpCarbohydrate.getText()));
            show();
        });
        Update.setOnAction(event -> {
            databaseHandler.UpdateDessert(UpName.getText(), UpType.getText(), Integer.parseInt(UpId.getText()));
            show();
        });
        Delete.setOnAction(event -> {
            databaseHandler.DeleteDessert(Integer.parseInt(UpId.getText()));
            show();
        });
        Profile.setOnAction(event -> {
            Profile.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(DessertCController.class.getResource("hello-view-con.fxml"));
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
        Orders.setOnAction(event -> {
            Orders.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(DessertCController.class.getResource("orderConfection.fxml"));
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
