package se_project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class LoginScreenController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    private User currentUser;
    private Stage stage;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        verifyLogin(event/*stage*/);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void verifyLogin(ActionEvent event/*Stage primaryStage*/) throws IOException {

        currentUser = new User(username.getText(), password.getText());

        if (currentUser.login()) {

            loadHomeScreen(currentUser.getUserType(username.getText()), event, currentUser.getAccount());
            

        } else {
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }
    }

    public void loadHomeScreen(String screenName, ActionEvent event, Account account) {
        try {

            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HomeScreen" + screenName + ".fxml"));
            Parent parent = loader.load();

            HomeScreenController hsc = loader.getController();
            hsc.initAccount(account);

            
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    currentUser.getAccountRecord().update();
                }
            });
           
            stage.setScene(new Scene(parent));

        } catch (IOException ex) {

        }
    }

    @FXML
    public void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }
}
