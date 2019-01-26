package se_project;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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

public class FinanceLoginScreenController implements Initializable {

    @FXML
    private JFXButton exitButton;
    @FXML
    private JFXButton enterButton;
    @FXML
    private JFXPasswordField specialWord;

    private Stage stage;
    private Account account;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleAction(ActionEvent event) {
        if (account == null) {
            System.out.println("account is null");
        }
        if (specialWord == null) {
            System.out.println("specialword is null");
        }

        if (((Student) account).getSpecialWord().equals(specialWord.getText())) {
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("FinanceScreen.fxml"));
                Parent parent = loader.load();

                FinanceScreenController fsc = loader.getController();
                fsc.initAccount(account);
                fsc.handleResetButton(null);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(new Scene(parent));

            } catch (IOException e) {

            }
        } else {
            specialWord.getStyleClass().add("wrong-credentials");
        }

    }

    public void setExitAction(ActionEvent event) {

        loadHomeScreen("Student", event);
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
