package se_project;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RemoveAccountScreenController implements Initializable {

    @FXML
    private JFXButton removeAccountButton;
    @FXML
    private JFXButton exitButton;
    private Account account;
    private Stage stage;

    @FXML
    private JFXTextField usernameField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        usernameField.setStyle("@css/login-screen.css");
    }

    public void handleRemoveAccountAction(ActionEvent event) {

        if (((Administrator) account).getAccountRecord().removeAccount(usernameField.getText())) {
            usernameField.clear();

        } else {

            usernameField.getStyleClass().add("wrong-credentials");
        }

    }

    public void setExitAction(ActionEvent event) {

        loadHomeScreen("Administrator", event);
    }

    public void loadHomeScreen(String screenName, ActionEvent event) {
        try {
            System.out.println("HomeScreen" + screenName + ".fxml");
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
