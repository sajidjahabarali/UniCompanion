
package se_project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
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

public class AddStudentEventController implements Initializable {

    @FXML
    private JFXButton exitButton;
    @FXML
    private JFXListView<Label> listViewStudentEvent;
    @FXML
    private JFXButton addStudentEventButton;
    @FXML
    private JFXButton removeStudentEventButton;
    private Account account;
    private Stage stage;
    private ArrayList<Event> studentEventArrayList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ToDo
    }

    public void handleAddEventAction(ActionEvent event) {
        try {
            AddEventAlertBox.display("Add event", "Enter event details:");
            listViewStudentEvent.getItems().clear();
            //Access event list, then add events added to event list
            studentEventArrayList = (new Event()).getEventList();
            int listSize = studentEventArrayList.size();

            //Create if statement to check if list has any events, if so, loop through all list events adding events to
            // listView (through a label) so they appear in the  event screen.
            if (listSize > 0) {
                for (int i = 0; i < listSize; i++) {
                    Label label1 = new Label();
                    label1.setText("Name: " + studentEventArrayList.get(i).getEventName() + "\n"
                            + "Date: " + studentEventArrayList.get(i).getEventDate() + "\n"
                            + "Type: " + studentEventArrayList.get(i).getEventType() + "\n"
                            + "Time: " + studentEventArrayList.get(i).getEventStartTime() + "\n"
                            + "Duration: " + studentEventArrayList.get(i).getEventDuration() + "\n"
                    );
                    label1.setStyle("-fx-font-style:italic;-fx-font-size:16px;-fx-font-family:Georgia;");
                    listViewStudentEvent.getItems().add(label1);
                }
            }
        }catch(Exception e) {

        }
    }

    public void removeFromList(JFXListView<Label> listView, ArrayList<?> arrayList) {
        int minus = listView.getSelectionModel().getSelectedIndex();
        arrayList.remove(minus);
        listView.getItems().remove(minus);
    }

    public void handleRemoveEventAction(ActionEvent event) {
        try {
            removeFromList(listViewStudentEvent, studentEventArrayList);
        } catch (Exception e) {

        }
    }

    public void handleLogOutButton(ActionEvent event) {
        loadHomeScreen("Lecturer", event);
    }

    @FXML
    public void setExitAction(ActionEvent event) {
        loadHomeScreen("Lecturer", event);
    }

    public void loadHomeScreen(String screenName, ActionEvent event) {
        try {
            System.out.println("HomeScreen" + screenName + ".fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HomeScreen" + screenName + ".fxml"));
            Parent parent = loader.load();

            HomeScreenController hsc = loader.getController();
            hsc.initAccount(account);

            //Parent parent = FXMLLoader.load(getClass().getResource("HomeScreen" + screenName + ".fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //stage.setTitle("UniCompanion");
            stage.setScene(new Scene(parent));
            //stage.show();
        } catch (IOException e) {

        }
    }

    public void initAccount(Account account) {

        this.account = account;
    }
}
