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

public class FeatureScreenController implements Initializable {

    protected Account account;
    @FXML
    private JFXButton exitButton;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void closeStage(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void setExitAction(ActionEvent event) {

    }

}
