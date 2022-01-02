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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Administrator
 */
public class maincont implements Initializable {
    
    @FXML
    private AnchorPane main;
    
    @FXML
    void studsign(ActionEvent event) throws IOException {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            main.getScene().getWindow().hide();
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
        });
        pt.play();
    }

    @FXML
    void teachsign(ActionEvent event) {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            main.getScene().getWindow().hide();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/layout/teachlog.fxml"));
                Stage s = new Stage();
                Scene scene = new Scene(root);
                s.setScene(scene);
                s.setTitle("exam");
                s.show();
            } catch (IOException ex) {
                Logger.getLogger(signcont.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        pt.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
    }

}

