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

public class LibraryScreenController implements Initializable {

    private JFXButton searchBooksButton;
    private JFXButton borrowedBooksButton;
    private JFXButton exitButton;
    private Stage stage;
    private Account account;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleSearchBooksButton(ActionEvent event) {
        loadScreen("SearchBooksScreen", event, new SearchBooksScreenController());
    }

    public void handleBorrowedBooksButton(ActionEvent event) {
        loadScreen("BorrowedBooksScreen", event, new BorrowedBooksScreenController());
    }

    public void setExitAction(ActionEvent event) {
        if (account instanceof Student) {
            System.out.println("student");
            loadHomeScreen("Student", event);
        } else if (account instanceof Lecturer) {
            System.out.println("lecturer");
            loadHomeScreen("Lecturer", event);
        } else if (account instanceof Administrator) {
            System.out.println("admin");
            loadHomeScreen("Administrator", event);
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

    @FXML

    public void loadScreen(String screenName, ActionEvent event, SearchBooksScreenController sc) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(screenName + ".fxml"));
            Parent parent = loader.load();

            
            sc = loader.getController();
            sc.initAccount(account);
           
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(new Scene(parent));
           
        } catch (IOException ex) {

        }
    }

    public void loadScreen(String screenName, ActionEvent event, BorrowedBooksScreenController sc) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(screenName + ".fxml"));
            Parent parent = loader.load();

            
            sc = loader.getController();
            sc.initAccount(account);
            
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(new Scene(parent));
            
        } catch (IOException ex) {

        }
    }

    public void initAccount(Account account) {
        this.account = account;
    }

}
