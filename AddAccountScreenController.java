package se_project;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddAccountScreenController implements Initializable {

    ObservableList list = FXCollections.observableArrayList("Administrator", "Lecturer", "Student");
    @FXML
    private ChoiceBox accountTypeBox;
    @FXML
    private JFXButton addAccountButton;
    @FXML
    private JFXButton exitButton;
    private Account account;
    private Stage stage;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;
    private Label message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountTypeBox.setValue("Student");
        accountTypeBox.setItems(list);

        usernameField.setStyle("@css/login-screen.css");
        passwordField.setStyle("@css/login-screen.css");

        message = new Label();
    }

    public void manage(ActionEvent event) {

        if (accountTypeBox.getValue().equals("Administrator")) {
            if (((Administrator) account).getAccountRecord().addAccount(usernameField.getText(), passwordField.getText(), 'a')) {
                usernameField.clear();
                passwordField.clear();

            } else {
                usernameField.getStyleClass().add("wrong-credentials");
                passwordField.getStyleClass().add("wrong-credentials");
            }
        } else if (accountTypeBox.getValue().equals("Student")) {
            if (((Administrator) account).getAccountRecord().addAccount(usernameField.getText(), passwordField.getText(), 's')) {
                usernameField.clear();
                passwordField.clear();

            } else {
                usernameField.getStyleClass().add("wrong-credentials");
                passwordField.getStyleClass().add("wrong-credentials");
            }
        } else if (accountTypeBox.getValue().equals("Lecturer")) {
            if (((Administrator) account).getAccountRecord().addAccount(usernameField.getText(), passwordField.getText(), 'l')) {
                usernameField.clear();
                passwordField.clear();

            } else {
                usernameField.getStyleClass().add("wrong-credentials");
                passwordField.getStyleClass().add("wrong-credentials");
            }
        } else {
            System.out.println("Account type error.");
        }

    }

    public void handleLogOutButton(ActionEvent event) {
        loadHomeScreen("Administrator", event);
    }

    @FXML
    public void setExitAction(ActionEvent event) {
        loadHomeScreen("Administrator", event);
    }

    @FXML
    private void closeStage(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
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
