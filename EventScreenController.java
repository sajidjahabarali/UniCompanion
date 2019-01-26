package se_project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.*;
import java.time.LocalDate;

public class EventScreenController implements Initializable {

    @FXML
    private JFXButton exitButton;
    @FXML
    private JFXButton addEventButton;
    @FXML
    private JFXButton removeEventButton;
    @FXML
    private JFXButton addAlarmButton;
    @FXML
    private JFXButton removeAlarmButton;
    @FXML
    private JFXListView<Label> listViewEvent;
    @FXML
    private JFXListView<Label> listViewAlarm;
    private Account account;
    private Stage stage;
    private ArrayList<Event> eventArrayList;
    private ArrayList<Alarm> alarmArrayList;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleAddEventAction(ActionEvent event) {
        AddEventAlertBox.display("Add event", "Enter event details:");
        listViewEvent.getItems().clear();
        //Access event list, then add events added to event list
        eventArrayList = (new Event()).getEventList();
        int listSize = eventArrayList.size();

        //Create if statement to check if list has any events, if so, loop through all list events adding events to
        // listView (through a label) so they appear in the  event screen.
        if (listSize > 0) {
            for (int i = 0; i < listSize; i++) {
                String isToday = "";
                if (eventArrayList.get(i).getEventDate().isEqual(LocalDate.now())) {
                    isToday = "Today\n";
                }
                Label label1 = new Label();
                label1.setText(isToday
                        + "Name: " + eventArrayList.get(i).getEventName() + "\n"
                        + "Date: " + eventArrayList.get(i).getEventDate() + "\n"
                        + "Type: " + eventArrayList.get(i).getEventType() + "\n"
                        + "Time: " + eventArrayList.get(i).getEventStartTime() + "\n"
                        + "Duration: " + eventArrayList.get(i).getEventDuration() + "\n"
                );
                if (eventArrayList.get(i).getEventDate().isEqual(LocalDate.now())) {
                    label1.setStyle("-fx-font-style:italic;-fx-font-size:16px;-fx-font-family:Georgia;-fx-font-weight: bold");
                } else {
                    label1.setStyle("-fx-font-style:italic;-fx-font-size:14px;-fx-font-family:Georgia;");
                }
                listViewEvent.getItems().add(label1);
            }
        }
    }

    public void handleRemoveEventAction(ActionEvent event) {
        try {
            removeFromList(listViewEvent, eventArrayList);
        } catch (Exception e) {

        }
    }

    public void handleAddAlarmAction(ActionEvent event) {
        AddAlarmAlertBox.display("Add Alarm", "Enter alarm details:");
        listViewAlarm.getItems().clear();

        //Access event list, then add events added to event list
        alarmArrayList = (new Alarm()).getAlarmList();
        int listSizeA = alarmArrayList.size();

        //Create if statement to check if list has any alarms, if so, loop through all list alarms adding events to
        // listView (through a label) so they appear in the  alarm screen.
        if (listSizeA > 0) {
            for (int i = 0; i < listSizeA; i++) {
                String daysActive = "";

                if (alarmArrayList.get(i).isMon()) {
                    daysActive += "| Mon |";
                }
                if (alarmArrayList.get(i).isTue()) {
                    daysActive += "| Tue |";
                }
                if (alarmArrayList.get(i).isWed()) {
                    daysActive += "| Wed |";
                }
                if (alarmArrayList.get(i).isThu()) {
                    daysActive += "| Thu |";
                }
                if (alarmArrayList.get(i).isFri()) {
                    daysActive += "| Fri |";
                }
                if (alarmArrayList.get(i).isSat()) {
                    daysActive += "| Sat |";
                }
                if (alarmArrayList.get(i).isSun()) {
                    daysActive += "| Sun |";
                }

                Label label3 = new Label();

                label3.setText("Name: " + alarmArrayList.get(i).getAlarmName() + "\n"
                        + "Time: " + alarmArrayList.get(i).getAlarmTime() + "\n"
                        + "Days: " + daysActive + "\n"
                );
                label3.setStyle("-fx-font-style:italic;-fx-font-size:14px;-fx-font-family:Georgia;");
                listViewAlarm.getItems().add(label3);
            }
        }

    }

    public void handleRemoveAlarmAction(ActionEvent event) {
        try {
            removeFromList(listViewAlarm, alarmArrayList);
        } catch (Exception e) {

        }
    }

    //create method to remove items from a listView
    public void removeFromList(JFXListView<Label> listView, ArrayList<?> arrayList) {
        int minus = listView.getSelectionModel().getSelectedIndex();
        arrayList.remove(minus);
        listView.getItems().remove(minus);
    }

    public void setExitAction(ActionEvent event) {
        //closeStage(event);
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
