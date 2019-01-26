package se_project;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AccountManagementScreenController implements Initializable {

    @FXML
    private JFXButton exitButton;
    @FXML
    private JFXButton manageButton;

    protected AccountRecord accountRecord;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void setExitAction(ActionEvent event) {

        loadHomeScreen("HomeScreenAdministrator");
    }

    public void manage(ActionEvent event) {

    }

    public void loadHomeScreen(String screenName) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(screenName + ".fxml"));
            Stage children = new Stage(StageStyle.DECORATED);
            children.setTitle("UniCompanion");
            children.setScene(new Scene(parent));
            children.show();
        } catch (IOException ex) {

        }
    }

}
