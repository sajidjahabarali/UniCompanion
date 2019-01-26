package se_project;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.time.LocalDate;

public class AddEventAlertBox extends Event {

    public static void display(String title, String message) {
        try {
            Stage window = new Stage();

            //Block user interaction with other windows until this one is closed
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(250);

            //Create GridPane
            GridPane eventAlertGrid = new GridPane();
            eventAlertGrid.setPadding(new Insets(10, 10, 10, 10));
            eventAlertGrid.setHgap(10);
            eventAlertGrid.setVgap(8);

            //Create label's and textFields, set GridPane constraints
            Label header = new Label(message);
            GridPane.setConstraints(header, 0, 0, 2, 1);

            Label nameLabel = new Label("Event name:");
            GridPane.setConstraints(nameLabel, 0, 2);

            TextField eventName1 = new TextField();
            eventName1.setPrefWidth(200);
            eventName1.setPromptText("Enter event name");
            GridPane.setConstraints(eventName1, 1, 2, 2, 1);

            Label typeLabel = new Label("Event type:");
            GridPane.setConstraints(typeLabel, 0, 3);

            ChoiceBox<String> eventType = new ChoiceBox<>();
            eventType.getItems().addAll("Lecture", "Tutorial", "Lab", "Exam", "Social");
            eventType.setValue("Lecture");
            eventType.setPrefWidth(200);
            GridPane.setConstraints(eventType, 1, 3, 2, 1);

            Label dateLabel = new Label("Event date:");
            GridPane.setConstraints(dateLabel, 0, 4);

            DatePicker datePicker = new DatePicker();
            datePicker.setPromptText("Select event date");
            datePicker.setPrefWidth(200);
            GridPane.setConstraints(datePicker, 1, 4, 2, 1);

            Label timeLabel = new Label("Event time:");
            GridPane.setConstraints(timeLabel, 0, 5);

            TextField eventHour = new TextField();
            eventHour.setPrefWidth(90);
            eventHour.setPromptText("Hour (24)");
            GridPane.setConstraints(eventHour, 1, 5, 1, 1);

            TextField eventMinute = new TextField();
            eventMinute.setPrefWidth(90);
            eventMinute.setPromptText("Minute (60)");
            GridPane.setConstraints(eventMinute, 2, 5, 1, 1);

            Label durationLabel = new Label("Event duration:");
            GridPane.setConstraints(durationLabel, 0, 6);

            ChoiceBox<String> eventDuration = new ChoiceBox<>();
            eventDuration.getItems().addAll("1 hour", "2 hours", "3 hours", "4 hours", "5 hours", "6 hours",
                    "All day");
            eventDuration.setValue("2 hours");
            eventDuration.setPrefWidth(200);
            GridPane.setConstraints(eventDuration, 1, 6, 2, 1);

            //add save button, call saveButtonClicked method, then close alert dialog
            Button saveButton = new Button("Save");
            saveButton.setOnAction(e -> {
                saveButtonClicked(eventName1.getText(), eventType.getValue(), datePicker.getValue(),
                        Integer.parseInt(eventHour.getText()), Integer.parseInt(eventMinute.getText()),
                        eventDuration.getValue());
                window.close();
            });
            GridPane.setConstraints(saveButton, 1, 7);
            //add cancel button, close window on click
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> window.close());
            GridPane.setConstraints(cancelButton, 2, 7);

            //Add items to GridPane to make them viewable
            eventAlertGrid.getChildren().addAll(header, nameLabel, eventName1, typeLabel, eventType, dateLabel, datePicker,
                    timeLabel, eventHour, eventMinute, durationLabel, eventDuration, saveButton,
                    cancelButton);
            //set borderPane as scene
            Scene scene = new Scene(eventAlertGrid);
            window.setScene(scene);
            window.showAndWait();
        }catch(Exception e){

        }

    }

    //this method is called when save event button is clicked in alert dialog, it validates information about event, if so, 
    //it creates event and adds it to the array list to be displayed on events screen.
    public static void saveButtonClicked(String name, String type, LocalDate date, int hour, int minute, String duration) {
        //check if minute and hour numbers are valid, then format correctly
        hour = ((hour >= 0 && hour < 24) ? hour : 0);
        minute = ((minute >= 0 && minute < 60) ? minute : 0);
        String finalTime = String.format("%02d:%02d", hour, minute);

        Event event = new Event();
        event.setEventName(name);
        event.setEventType(type);
        event.setEventDate(date);
        event.setEventStartTime(finalTime);
        event.setEventDuration(duration);
        eventList.add(event);
    }

}
