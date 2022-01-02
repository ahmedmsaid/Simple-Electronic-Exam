/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxpro.MySQLAccess;

/**
 *
 * @author Administrator
 */
public class teachlogcont  implements Initializable{
    @FXML
    private AnchorPane teachlog;
    
    @FXML
    private AnchorPane teachexam;

    @FXML
    private TextField SignInEmail;

    @FXML
    private PasswordField SignInPassword;

    @FXML
    private Label invalidLabel;

    @FXML
    void logaction(ActionEvent event) {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            System.out.println("log in");
            String email = SignInEmail.getText();
            String pass = SignInPassword.getText();
            Parent root;
            if(MySQLAccess.searchTeacher(email, pass)){
            teachlog.getScene().getWindow().hide();
            try {
                root = FXMLLoader.load(getClass().getResource("/layout/teachexam.fxml"));
                Stage s = new Stage();
                Scene scene = new Scene(root);
                s.setScene(scene);
                s.setTitle("exam");
                s.show();
            } catch (IOException ex) {
                Logger.getLogger(logcont.class.getName()).log(Level.SEVERE, null, ex);
            }}
            else {
                invalidLabel.setText("Invalid Credentials!");
                System.out.println("Invalid");
            }

        });
        pt.play();
    }
    @FXML
    void pageup(ActionEvent event) throws IOException {
        teachlog.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/layout/teachsign.fxml"));
        Stage s = new Stage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.setTitle("exam");
        s.show();
    }
    
    @FXML
    void pass(ActionEvent event) {
        teachlog.getScene().getWindow().hide();
        Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/layout/teachforgotpass.fxml"));
                Stage s = new Stage();
                Scene scene = new Scene(root);
                s.setScene(scene);
                s.setTitle("exam");
                s.show();
            } catch (IOException ex) {
                Logger.getLogger(logcont.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //To change body of generated methods, choose Tools | Templates.
    }
}
