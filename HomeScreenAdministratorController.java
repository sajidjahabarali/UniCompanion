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
import javafx.stage.StageStyle;


public class HomeScreenAdministratorController extends HomeScreenController implements Initializable {

    @FXML
    private JFXButton addAccountButton;
    @FXML
    private JFXButton removeAccountButton;
    @FXML
    private JFXButton changeAccountPasswordButton;
    @FXML
    private JFXButton libraryButton;
    @FXML
    private JFXButton logOutButton;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    
    public void handleAddAccountButton(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddAccountScreen.fxml"));
            Parent parent = loader.load();

            AddAccountScreenController asc = loader.getController();
            asc.initAccount(account);


            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(new Scene(parent));
            
        } catch (IOException ex) {

        }
    }
    
    public void handleRemoveAccountButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RemoveAccountScreen.fxml"));
            Parent parent = loader.load();

            RemoveAccountScreenController rsc = loader.getController();
            rsc.initAccount(account);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(new Scene(parent));
            
        } catch (IOException ex) {

        }
    }
    
    public void handleChangePasswordButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ChangeAccountPasswordsScreen.fxml"));
            Parent parent = loader.load();

            ChangeAccountPasswordsScreenController csc = loader.getController();
            csc.initAccount(account);


            
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(new Scene(parent));
            
        } catch (IOException ex) {

        }
    }

    public void handleLibraryButton(ActionEvent event) {
        

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LibraryScreen.fxml"));
            Parent parent = loader.load();

            LibraryScreenController lsc = loader.getController();
            lsc.initAccount(account);


            
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           
            stage.setScene(new Scene(parent));
            
        } catch (IOException ex) {

        }
    }

    public void handleLogOutButton(ActionEvent event){
        super.handleLogOutButton(event);
        ((Administrator)account).getAccountRecord().update();
    }


    public void handleShutDownSystemButton(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ShutDownSystemScreen.fxml"));
            Parent parent = loader.load();

            ShutDownSystemScreenController sdsc = loader.getController();
            sdsc.initAccount(account);


            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(new Scene(parent));
            
        } catch (IOException ex) {

        }
    }
    
    
}
