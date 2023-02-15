package com.example.bd;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.example.bd.animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text count;

    @FXML
    private TextField Login;

    @FXML
    private PasswordField Password;

    @FXML
    private Button Registration;

    @FXML
    private Button SignIn;

    @FXML
    private Button Confection;

    public String loginText;

    @FXML
    void initialize() {
        SignIn.setOnAction(event -> {
            loginText = Login.getText().trim();
            String passwordText = Password.getText().trim();

            if(!loginText.equals("") && !passwordText.equals("")) {
                loginUser(loginText,passwordText);
            }
            else {
                System.out.println("Login or password is empty");
            }
        });
        Registration.setOnAction(event -> {
            Registration.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("SignUp.fxml"));
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
        Confection.setOnAction(event -> {
            Confection.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("hello-view-con.fxml"));
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
    }

    private void loginUser(String loginText, String passwordText) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getUserCustomer(Login.getText(),Password.getText());
        int counter = 0;
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }
        if (counter>=1  ) {
            System.out.println("Succes!");
            SignIn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("main-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        else
        {
            Shake userblok = new Shake(Login);
            Shake userblokP = new Shake(Password);
            userblok.playAnim();
            userblokP.playAnim();
        }
    }
}
