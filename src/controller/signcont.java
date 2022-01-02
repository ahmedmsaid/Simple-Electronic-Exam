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
public class signcont implements Initializable {

    @FXML
    private AnchorPane sign;
    
    @FXML
    private TextField Year;

    @FXML
    private PasswordField SignUpPassword;
    
    @FXML
    private PasswordField SignUpConfirmPassword;

    @FXML
    private TextField SignUpUserName;

    @FXML
    private TextField SignUpEmail;
    
    @FXML
    private Label alertLabel;


    @FXML
    void pagein(ActionEvent event) throws IOException {
        sign.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/layout/log.fxml"));
        Stage s = new Stage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.setTitle("exam");
        s.show();
    }

    @FXML
    void signup(ActionEvent event) {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            String username = SignUpUserName.getText();
            String pass = SignUpPassword.getText();
            String confirmPass = SignUpConfirmPassword.getText();
            String email = SignUpEmail.getText();
            String year = Year.getText();
            if(MySQLAccess.existStudent(email)){
                alertLabel.setVisible(true);
            }
            else if (!pass.equals(confirmPass)){
                alertLabel.setText("Password is not matched");
                alertLabel.setVisible(true);
            }
            else {
            MySQLAccess.insertStudent(username, email, pass, year);
            System.out.println("sign up");
            sign.getScene().getWindow().hide();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/layout/log.fxml"));
                Stage s = new Stage();
                Scene scene = new Scene(root);
                s.setScene(scene);
                s.setTitle("exam");
                s.show();
            } catch (IOException ex) {
                Logger.getLogger(signcont.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});
        pt.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
    }

}
