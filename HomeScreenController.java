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

public class HomeScreenController implements Initializable {

    @FXML
    private JFXButton libraryButton;
    @FXML
    private JFXButton logOutButton;
    protected Account account;
    protected Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void loadScreen(String screenName, ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(screenName + ".fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(parent));

        } catch (IOException ex) {

        }
    }

    public void handleLogOutButton(ActionEvent event) {
        try {
            System.out.println("logged out");
            account.update();
            Parent parent = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(parent));

        } catch (IOException ex) {
            System.out.println("log out error");
        }
    }

    @FXML
    private void closeStage(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void initAccount(Account account) {
        this.account = account;
    }
}
