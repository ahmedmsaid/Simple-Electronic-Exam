/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author Administrator
 */
public class teachforgotpasscont implements Initializable{
     @FXML
    private AnchorPane forgot;

    @FXML
    private PasswordField firstdig;

    @FXML
    private PasswordField lastdig;

    @FXML
    private Label invalidLabel;
    
    @FXML
    private CheckBox first;
    
    @FXML
    private TextField UserName;
    
    @FXML
    private CheckBox second;
     @Override
    public void initialize(URL location, ResourceBundle resources) {
         //To change body of generated methods, choose Tools | Templates.
         first.setSelected(true);
         firstdig.setDisable(false);
         second.setDisable(true);
         lastdig.setDisable(true);
    }
    
    @FXML
    void first(ActionEvent event) {
        if (first.isSelected()) {
            second.setDisable(true);
            lastdig.setDisable(true);
        }else{
            second.setDisable(false);
            lastdig.setDisable(false);
        }
    }

    @FXML
    void last(ActionEvent event) {
        if (second.isSelected()) {
            first.setDisable(true);
            firstdig.setDisable(true);
        }else{
            first.setDisable(false);
            firstdig.setDisable(false);
        }
    }

    @FXML
    void logaction(ActionEvent event) {
        if (!first.isSelected() && !second.isSelected()) {
            invalidLabel.setText("you must check only one");
            PauseTransition pt = new PauseTransition();
            pt.setDuration(Duration.seconds(1));
            pt.setOnFinished(ev -> {
                invalidLabel.setText("");
            });
            pt.play();
            return;
        }
        if (first.isSelected()) {
            System.out.println(firstdig.getText());
        } else {
            System.out.println(lastdig.getText());
        }
    }

   
}
