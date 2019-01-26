package se_project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class FinanceScreenController implements Initializable {

    @FXML
    private JFXTextField currentBalance;
    @FXML
    private JFXTextField updatedBalance;
    @FXML
    private JFXButton depositButton;
    @FXML
    private JFXButton withdrawButton;
    @FXML
    private JFXButton okButton;
    @FXML
    private JFXButton resetButton;
    private Account account;
    private Stage stage;
    private GridPane financePane;
    private Label headerLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void display() {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_RIGHT);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label headerLabel = new Label("Finance");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(10, 0, 10, 0));

        Label currentLabel = new Label("Current balance:    £");
        gridPane.add(currentLabel, 0, 1);
        currentBalance.setPrefWidth(100);
        currentBalance.setPrefHeight(40);

        Label ActionLabel = new Label("Choose action: ");
        gridPane.add(ActionLabel, 0, 2);
        gridPane.add(withdrawButton, 1, 2);
        GridPane.setHalignment(withdrawButton, HPos.LEFT);
        Button depositButton = new Button("Deposit");
        gridPane.add(depositButton, 1, 2);
        GridPane.setHalignment(depositButton, HPos.RIGHT);

        Label BalanceLabel = new Label("Updated balance:   £");
        gridPane.add(BalanceLabel, 0, 3);
        updatedBalance.setPrefHeight(40);
        gridPane.add(updatedBalance, 1, 3);
        updatedBalance.setEditable(false);

        resetButton.setPrefHeight(40);
        resetButton.setDefaultButton(true);
        resetButton.setPrefWidth(50);
        gridPane.add(resetButton, 2, 4, 2, 1);
        GridPane.setHalignment(resetButton, HPos.CENTER);
        GridPane.setMargin(resetButton, new Insets(20, 0, 20, 0));

    }

    public void handleAction(ActionEvent event) {

    }

    public void handleDepositMoneyAction(ActionEvent event) {
        if (currentBalance.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Add your current balance");
            alert.showAndWait();
        } else {
            try {
                double depositAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter amount you would like to deposit"));
                double amountDeposit = Double.parseDouble(currentBalance.getText());
                double amountDepositFinal = depositAmount + amountDeposit;
                updatedBalance.setText(Double.toString(amountDepositFinal));
            }catch (NumberFormatException e){

            }
        }

    }

    public void handleWithdrawMoneyAction(ActionEvent event) {
        if (currentBalance.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Add your current balance");
            alert.showAndWait();
        } else {
            try {
                double withdrawAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter amount you would like to withdraw"));
                double amountWithdraw = Double.parseDouble(currentBalance.getText());
                double amountwithdrawFinal = amountWithdraw - withdrawAmount;


            if (amountwithdrawFinal < 0) {
                updatedBalance.setText(Double.toString(amountwithdrawFinal));
                updatedBalance.setStyle("-fx-text-fill: red;");
            } else {
                updatedBalance.setText(Double.toString(amountwithdrawFinal));
                updatedBalance.setStyle("-fx-text-fill: black;");
            }
            }catch(NumberFormatException e){

            }
        }
    }

    public void setExitAction(ActionEvent event) {

        loadHomeScreen("Student", event);
    }

    public void handleResetButton(ActionEvent event) {
        try {
            currentBalance.setText(Double.toString(((Student) account).getBalance()));

            updatedBalance.clear();
        } catch (Exception e) {

        }
    }

    public void handleOkButton(ActionEvent event) {
        try {
            if (!updatedBalance.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                ((Student) account).setBalance(Double.parseDouble(updatedBalance.getText()));
                alert.setContentText("Your balance has been updated");
                alert.showAndWait();
            }
        } catch (Exception e) {

        }
    }

    public void loadHomeScreen(String screenName, ActionEvent event) {
        try {
            System.out.println(screenName + ".fxml");
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
