/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxpro.MySQLAccess;

/**
 *
 * @author Administrator
 */
public class examcont implements Initializable{
    @FXML
    private AnchorPane exam;
    
    @FXML
    private ToggleGroup question;

    @FXML
    private RadioButton dChoice;

    @FXML
    private RadioButton bChoice;

    @FXML
    private RadioButton aChoice;

    @FXML
    private Label questionLabel;

    @FXML
    private RadioButton cChoice;
    static int counter = 0;
    static int questionNumber = 0;
    static int carrier = 0; // to prevent repeated questions
    static int grade = 0;
    Random rand = new Random();

    @FXML
    void gradeaction(ActionEvent event) {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            System.out.println("finish");
            logcont getEmail = new logcont();
            MySQLAccess.gradeUpdate(getEmail.email, grade);
            exam.getScene().getWindow().hide();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/layout/result.fxml"));
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
    void nextaction(ActionEvent event) {
//        PauseTransition pt = new PauseTransition();
//        pt.setDuration(Duration.seconds(1));
//        questionLabel.setText("Ha");
//        pt.setOnFinished(ev -> {
            String answer = MySQLAccess.getAnswer(questionNumber);
            checkAnswer(answer);
            questionNumber = rand.nextInt(17);
            while (questionNumber == carrier){
                questionNumber = rand.nextInt(17);
            }
            System.out.println("exam");
            Parent root;
            try {
                counter++;
                System.out.println(counter);
                if (counter < 5){
                    questionLabel.setText(MySQLAccess.getQuestion(questionNumber));
                    aChoice.setText(MySQLAccess.getChoiceA(questionNumber));
                    bChoice.setText(MySQLAccess.getChoiceB(questionNumber));
                    cChoice.setText(MySQLAccess.getChoiceC(questionNumber));
                    dChoice.setText(MySQLAccess.getChoiceD(questionNumber));   
//                System.out.println(MySQLAccess.getQuestion(questionNumber));
                //GUI
//                root = FXMLLoader.load(getClass().getResource("/layout/exam.fxml"));
//                Stage s = new Stage();
//                Scene scene = new Scene(root);
//                s.setScene(scene);
//                s.setTitle("exam");
//                s.show();
                }
                else {
                    logcont getEmail = new logcont();
                    MySQLAccess.gradeUpdate(getEmail.email, grade);
                    exam.getScene().getWindow().hide();
                    root = FXMLLoader.load(getClass().getResource("/layout/result.fxml"));
                    Stage s = new Stage();
                    Scene scene = new Scene(root);
                    s.setScene(scene);
                    s.setTitle("exam");
                    s.show();
                }
            } catch (IOException ex) {
                Logger.getLogger(signcont.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//);
//        pt.play();
//        }
    
    public void checkAnswer(String answer) {
        String choiceA = aChoice.getText();
        String choiceB = bChoice.getText();
        String choiceC = cChoice.getText();
        String choiceD = dChoice.getText();
        if (aChoice.isSelected()){
            //System.out.println(aChoice);
            if (choiceA.equals(answer)) grade++;
        }
        else if (bChoice.isSelected()){
          //System.out.println(aChoice);
           if (choiceB.equals(answer)) grade++;
        }
        else if (cChoice.isSelected()){
            //System.out.println(aChoice);
            if (choiceC.equals(answer)) grade++;
        }
        else if (dChoice.isSelected()){
            //System.out.println(aChoice);
           if (choiceD.equals(answer)) grade++;
        }
        else System.out.println("Error");
        System.out.println("done");
    }
    
//    @FXML
//    void bChoice(ActionEvent event) {
//        String choice = bChoice.getText();
//        String answer = MySQLAccess.getAnswer(questionNumber);
//        if (choice.equals(answer)){
//            grade++;
//        }
//    }
//    
//    @FXML
//    void cChoice(ActionEvent event) {
//        String choice = cChoice.getText();
//        String answer = MySQLAccess.getAnswer(questionNumber);
//        if (choice.equals(answer)){
//            grade++;
//        }
//    }
//    
//    @FXML
//    void dChoice(ActionEvent event) {
//        String choice = dChoice.getText();
//        String answer = MySQLAccess.getAnswer(questionNumber);
//        if (choice.equals(answer)){
//            grade++;
//        }
//    }
                
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
        Random rand = new Random();
        String answer = MySQLAccess.getAnswer(questionNumber);
        questionNumber = rand.nextInt(17);
        carrier = questionNumber;
        questionLabel.setText(MySQLAccess.getQuestion(questionNumber));
        aChoice.setText(MySQLAccess.getChoiceA(questionNumber));
        bChoice.setText(MySQLAccess.getChoiceB(questionNumber));
        cChoice.setText(MySQLAccess.getChoiceC(questionNumber));
        dChoice.setText(MySQLAccess.getChoiceD(questionNumber));
    }

}
