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

public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Dessert;

    @FXML
    private Button Exit;

    @FXML
    private TableColumn<Profile, String> NameColumn;

    @FXML
    private TableColumn<Profile, String> PatronomicColumn;

    @FXML
    private TableColumn<Profile, String> SurnameColumn;

    @FXML
    private Button add;

    @FXML
    private TextField name;

    @FXML
    private TextField name1;

    @FXML
    private TextField patronomic;

    @FXML
    private TextField surname;

    @FXML
    private Button Exit1;

    @FXML
    private TableView<Profile> tablee;

    ObservableList<Profile> list_D;

    //String log = HelloController.getLoginText();

    void show()
    {
        NameColumn.setCellValueFactory(new PropertyValueFactory<Profile, String>("Name"));
        SurnameColumn.setCellValueFactory(new PropertyValueFactory<Profile, String>("Surname"));
        PatronomicColumn.setCellValueFactory(new PropertyValueFactory<Profile, String>("Patronomic"));
        DatabaseHandler databaseHandler = new DatabaseHandler();
        list_D = databaseHandler.getProfile(name1.getText());
        tablee.setItems(list_D);
    }

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Exit1.setOnAction(event -> {
            show();
        });
        add.setOnAction(event -> {
            databaseHandler.UpdateProfile(name.getText(), surname.getText(), patronomic.getText(),name1.getText());
            show();
        });
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
    }

}
