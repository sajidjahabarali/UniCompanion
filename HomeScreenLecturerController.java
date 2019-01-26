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

public class HomeScreenLecturerController extends HomeScreenController implements Initializable {

    @FXML
    private JFXButton libraryButton;
    @FXML
    private JFXButton logOutButton;
    @FXML
    private JFXButton feedbackButton;
    @FXML
    private JFXButton addStudentEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeStage(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
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

    public void handleFeedbackButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FeedbackScreen.fxml"));
            Parent parent = loader.load();

            FeedbackScreenController lsc = loader.getController();
            lsc.initAccount(account);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(parent));

        } catch (IOException ex) {

        }
    }

    public void handleAddStudentEventButton(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddStudentEventScreen.fxml"));
            Parent parent = loader.load();

            AddStudentEventController adev = loader.getController();
            adev.initAccount(account);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(parent));

        } catch (IOException ex) {

        }
    }

}
