package se_project;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddAlarmAlertBox extends Alarm {

    public static void display(String title, String message) {
        Stage window = new Stage();

        //Block user interaction with other windows until this one is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        //Create GridPane
        GridPane alarmAlertGrid = new GridPane();
        alarmAlertGrid.setPadding(new Insets(10, 10, 10, 10));
        alarmAlertGrid.setHgap(10);
        alarmAlertGrid.setVgap(10);

        //Create label's and textFields, set GridPane constraints
        Label header = new Label(message);
        GridPane.setConstraints(header, 0, 0, 2, 1);

        Label nameLabel = new Label("Alarm name:");
        GridPane.setConstraints(nameLabel, 0, 2);

        TextField alarmName = new TextField();
        alarmName.setPrefWidth(200);
        alarmName.setPromptText("Enter alarm name");
        GridPane.setConstraints(alarmName, 1, 2, 2, 1);

        Label timeLabel = new Label("Alarm time:");
        GridPane.setConstraints(timeLabel, 0, 3);

        TextField alarmHour = new TextField();
        alarmHour.setPrefWidth(90);
        alarmHour.setPromptText("Hour (24)");
        GridPane.setConstraints(alarmHour, 1, 3, 1, 1);

        TextField alarmMinute = new TextField();
        alarmMinute.setPrefWidth(90);
        alarmMinute.setPromptText("Minute (60)");
        GridPane.setConstraints(alarmMinute, 2, 3, 1, 1);

        Label daysLabel = new Label("Alarm days:");
        GridPane.setConstraints(daysLabel, 0, 4);

        CheckBox days1 = new CheckBox("Monday");
        GridPane.setConstraints(days1, 1, 4);
        CheckBox days2 = new CheckBox("Tuesday");
        GridPane.setConstraints(days2, 1, 5);
        CheckBox days3 = new CheckBox("Wednesday");
        GridPane.setConstraints(days3, 1, 6);
        CheckBox days4 = new CheckBox("Thursday");
        GridPane.setConstraints(days4, 1, 7);
        CheckBox days5 = new CheckBox("Friday");
        GridPane.setConstraints(days5, 1, 8);
        CheckBox days6 = new CheckBox("Saturday");
        GridPane.setConstraints(days6, 1, 9);
        CheckBox days7 = new CheckBox("Sunday");
        GridPane.setConstraints(days7, 1, 10);

        //add save button, call saveButtonClicked method, then close alert dialog
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            saveButtonClicked(alarmName.getText(), Integer.parseInt(alarmHour.getText()),
                    Integer.parseInt(alarmMinute.getText()), days1, days2, days3, days4, days5, days6, days7);
            window.close();
        });
        GridPane.setConstraints(saveButton, 1, 12);

        //add cancel button, close window on click
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());
        GridPane.setConstraints(cancelButton, 2, 12);

        //Add items to GridPane to make them viewable
        alarmAlertGrid.getChildren().addAll(header, nameLabel, alarmName, timeLabel, alarmHour, alarmMinute, saveButton,
                cancelButton, daysLabel, days1, days2, days3, days4, days5, days6, days7);
        //set borderPane as scene
        Scene scene = new Scene(alarmAlertGrid);
        window.setScene(scene);
        window.showAndWait();
    }

    //this method is called when save event button is clicked in alert dialog, it checks that information added about
    //event is valid, if so, it creates event and adds it to the array list to be displayed on events screen.
    public static void saveButtonClicked(String name, int hour, int minute, CheckBox days1, CheckBox days2,
            CheckBox days3, CheckBox days4, CheckBox days5, CheckBox days6, CheckBox days7) {
        Alarm alarm = new Alarm();
        alarm.setAlarmName(name);

        hour = ((hour >= 0 && hour < 24) ? hour : 0);
        minute = ((minute >= 0 && minute < 60) ? minute : 0);
        String finalTime = String.format("%02d:%02d", hour, minute);

        alarm.setAlarmTime(finalTime);

        if (days1.isSelected()) {
            alarm.setMon(true);
        }
        if (days2.isSelected()) {
            alarm.setTue(true);
        }
        if (days3.isSelected()) {
            alarm.setWed(true);
        }
        if (days4.isSelected()) {
            alarm.setThu(true);
        }
        if (days5.isSelected()) {
            alarm.setFri(true);
        }
        if (days6.isSelected()) {
            alarm.setSat(true);
        }
        if (days7.isSelected()) {
            alarm.setSun(true);
        }

        alarmList.add(alarm);
    }

}
