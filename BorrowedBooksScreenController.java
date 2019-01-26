package se_project;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class BorrowedBooksScreenController implements Initializable {

    private static int Xdim = 450;
    private static int Ydim = 800;

    @FXML
    private JFXButton exitButton;
    @FXML
    private JFXButton renewAllButton;
    @FXML
    private JFXButton renewButton;

    private Stage stage;
    private Account account;
    private final SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    @FXML
    private ListView<Label> listView;
    private ArrayList<Book> borrowedBooks;
    private ArrayList<Calendar> dueDate;
    private ArrayList<Label> labelList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void displayBorrowedBooks(ActionEvent event) {
        for (BookItem bookI : account.getBorrowedBooks()) {
            Label label = new Label();
            Book b = bookI.getBookDetails();
            Calendar dueDate = bookI.getDueDate();
            String dDate = date.format(dueDate.getTime());
            label.setText("Title " + b.getTitle() + "\n" + "Author " + b.getAuthor() + "\n" + "Due Date " + dDate);
            listView.getItems().add(label);
        }
    }

    public void handleRenewAllBooksAction(ActionEvent event) {
        listView.getItems().clear();
        for (BookItem bookI : account.getBorrowedBooks()) {
            Label label = new Label();
            Book b = bookI.getBookDetails();
            Calendar dueDate = bookI.getDueDate();
            dueDate.add(Calendar.WEEK_OF_YEAR, 1);
            String dDate = date.format(dueDate.getTime());
            label.setText("Title " + b.getTitle() + "\n" + "Author "
                    + b.getAuthor() + "\n" + "Due Date " + dDate);
            listView.getItems().add(label);
        }
    }

    public void handleRenewBooksAction(ActionEvent event) {
        int index = listView.getSelectionModel().getSelectedIndex();
        createArrays();
        dueDate.get(index).add(Calendar.WEEK_OF_YEAR, 1);
        String dDate = date.format(dueDate.get(index).getTime());
        listView.getSelectionModel().getSelectedItem().setText("Title " + borrowedBooks.get(index).getTitle() + "\n" + "Author "
                + borrowedBooks.get(index).getAuthor() + "\n" + "Due Date " + dDate);
    }

    public void createArrays() {
        borrowedBooks = new ArrayList<Book>();
        dueDate = new ArrayList<Calendar>();
        for (BookItem bookI : account.getBorrowedBooks()) {
            borrowedBooks.add(bookI.getBookDetails());
            dueDate.add(bookI.getDueDate());
        }
    }

    public void setExitAction(ActionEvent event) {
        
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

    public void loadScreen(String screenName, ActionEvent event) {
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

    public void initAccount(Account account) {
        this.account = account;
        System.out.println(account.getUsername());
    }
}
