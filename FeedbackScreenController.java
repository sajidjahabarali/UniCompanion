/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se_project;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mande
 */
public class FeedbackScreenController implements Initializable {

    @FXML
    private JFXButton exitButton;
    @FXML
    private JFXButton submitButton;
    private Account account;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setExitAction(ActionEvent event) {
         if (account instanceof Student) {
            System.out.println("student");
            loadHomeScreen("Student", event);
        } else if (account instanceof Lecturer) {
            System.out.println("lecturer");
            loadHomeScreen("Lecturer", event);
        } else {
            System.out.println("error");
            System.exit(0);
        }
    }

    public void loadHomeScreen(String screenName, ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HomeScreen" + screenName + ".fxml"));
            Parent parent = loader.load();

            HomeScreenController hsc = loader.getController();
            hsc.initAccount(account);

            
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(new Scene(parent));
            
        } catch (IOException e) {

        }
    }

    public void initAccount(Account account) {
        this.account = account;
    }

}
