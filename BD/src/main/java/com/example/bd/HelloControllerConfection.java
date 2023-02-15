package com.example.bd;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.bd.animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloControllerConfection {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public TextField Login;

    @FXML
    private PasswordField Password;

    @FXML
    private Button Registration;

    @FXML
    private Button SignIn;

    @FXML
    void initialize() {
        SignIn.setOnAction(event -> {
            String loginText = Login.getText().trim();
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
            FXMLLoader loader = new FXMLLoader(HelloControllerConfection.class.getResource("SignUpConfection.fxml"));
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

    private void loginUser(String loginText, String passwordText) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getUserConfection(Login.getText(),Password.getText());
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
            FXMLLoader loader = new FXMLLoader(HelloControllerConfection.class.getResource("mainC-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
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
