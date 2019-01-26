package se_project;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SearchBooksScreenController implements Initializable {

    private ObservableList facultiesList = FXCollections.observableArrayList("All Faculties", "Computer Science", "Fiction");
    @FXML
    private ChoiceBox facultiesBox;
    @FXML
    private JFXButton exitButton;
    @FXML
    private JFXButton searchButton;
    private Stage stage;
    private Account account;

    private ArrayList<Book> books;
    private ArrayList<JFXButton> borrowButtons;
    private int bookNamesXPos;
    private int borrowButtonsXPos;
    private int bookNamesYPos;
    private int borrowButtonsYPos;
    private Label currentBorrowedLabel;
    private Label currentBookName;
    private JFXButton currentBorrowButton;
    private String bookName;
    private String faculty;

    @FXML
    private GridPane bookListPane;
    private ArrayList<Label> borrowedLabels;
    private ArrayList<Integer> borrowYPos;

    @FXML
    private JFXTextField bookNameField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facultiesBox.setValue("All Faculties");
        facultiesBox.setItems(facultiesList);
        bookListPane.setHgap(10);
        bookListPane.setVgap(10);
    }

    public void handleSearchAction(ActionEvent event) throws IOException {
        books = new ArrayList<Book>();
        borrowYPos = new ArrayList<Integer>();
        borrowButtons = new ArrayList<JFXButton>();

        bookListPane.getChildren().clear();
        bookName = bookNameField.getText();
        faculty = facultiesBox.getValue().toString();
        bookNamesXPos = 0;
        borrowButtonsXPos = 9;
        bookNamesYPos = 0;
        borrowButtonsYPos = 0;
        if (bookName == null || bookName.equals("")) {
            if (faculty == null || faculty.equals("All Faculties")) {

                for (Catalog a : account.getLibrary().getCatalogs()) {

                    for (Book b : a.getBooks()) {

                        viewBook(b);

                    }

                }
            } else {

                if (account.getLibrary().getCatalog(faculty) == null) {
                    System.out.println("account is null");
                }
                if (faculty == null) {
                    System.out.println("faculty is null");
                }

                for (Book b : account.getLibrary().getCatalog(faculty).getBooks()) {

                    if (b == null) {
                        System.out.println("book is null");
                    }

                    viewBook(b);

                }
            }

        } else {

            if (faculty == null || faculty.equals("All Faculties")) {

                for (Catalog a : account.getLibrary().getCatalogs()) {

                    for (Book b : a.getBooks()) {

                        if ((b.getTitle().equals(bookNameField.getText())) || (b.getIsbn().equals(bookNameField.getText()))) {
                            viewBook(b);

                        }

                    }

                }
            } else {

                if (account == null) {
                    System.out.println("account is null");
                }
                if (account.getLibrary() == null) {
                    System.out.println("library is null");
                }
                if (account.getLibrary().getCatalog(faculty) == null) {
                    System.out.println("catalog is null");
                }

                for (Book b : account.getLibrary().getCatalog(faculty).getBooks()) {

                    if ((b.getTitle().equals(bookNameField.getText())) || (b.getIsbn().equals(bookNameField.getText()))) {
                        viewBook(b);

                    }

                }

            }

        }

        addButtonFunctionality();

    }

    private void addButtonFunctionality() {
        borrowedLabels = new ArrayList<Label>();

        for (Button a : borrowButtons) {
            currentBorrowedLabel = new Label("Borrowed");
            currentBorrowedLabel.setMinWidth(100);
            currentBorrowedLabel.setStyle("-fx-font-style:italic;-fx-font-size:14px;-fx-font-family:Georgia");
            borrowedLabels.add(currentBorrowedLabel);
            a.setStyle("-fx-border-color:#000000;-fx-border-radius:50px; -fx-background-radius:50px;-fx-font-style:italic;-fx-font-size:14px;-fx-font-family:Georgia");
            borrowedLabels.get(borrowButtons.indexOf(a)).setPrefWidth(60);
            if (a == null) {
                System.out.println("Button doesnt exist");
            } else {

            }

            a.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    account.borrowBook(books.get(borrowButtons.indexOf(a)).getCopy());

                    bookListPane.getChildren().remove(a);

                    GridPane.setConstraints(borrowedLabels.get(borrowButtons.indexOf(a)), borrowButtonsXPos, borrowYPos.get(borrowButtons.indexOf(a)));
                    bookListPane.getChildren().add(borrowedLabels.get(borrowButtons.indexOf(a)));

                }
            });

        }

    }

    private void viewBook(Book b) {

        if ((b.getQuantity() > 0) && (b.isAvailable())) {
            currentBookName = new Label(b.getTitle());
            currentBookName.setMinWidth(Label.USE_PREF_SIZE);
            currentBookName.setStyle("-fx-font-style:italic;-fx-font-size:14px;-fx-font-family:Georgia");
            currentBorrowButton = new JFXButton("Borrow");
            currentBorrowButton.setStyle("-fx-border-color:#000000;-fx-border-radius:50px; -fx-background-radius:50px;-fx-font-style:italic;-fx-font-size:14px;-fx-font-family:Georgia");

            currentBorrowButton.setMinWidth(Button.USE_PREF_SIZE);

            books.add(b);
            borrowButtons.add(currentBorrowButton);
            borrowYPos.add(bookNamesYPos);

            GridPane.setConstraints(currentBookName, bookNamesXPos, bookNamesYPos);
            GridPane.setConstraints(currentBorrowButton, borrowButtonsXPos, bookNamesYPos);

            bookListPane.getChildren().addAll(currentBookName, currentBorrowButton);

            bookNamesYPos++;
            borrowButtonsYPos++;

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

    @FXML
    private void closeStage(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void loadHomeScreen(String screenName, ActionEvent event) {
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
    }
}
