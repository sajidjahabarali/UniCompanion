package se_project;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

public class Account {

    protected String feedback;
    protected String username;
    protected String password;
    protected Library library;
    protected ArrayList<BookItem> borrowedBooks;
    private File accountFile;
    private FileReader accountFileReader;
    private Scanner contentReader;
    private FileWriter accountFileWriter;
    private BufferedWriter contentWriter;
    private String currentBookID;

    public Account(String username, String password, Library library) {
        borrowedBooks = new ArrayList<BookItem>();

        this.username = username;
        this.password = password;

        this.library = library;

    }

    public void update() {

        try {
            accountFileWriter = new FileWriter(username + ".txt");

            contentWriter = new BufferedWriter(accountFileWriter);

            for (BookItem a : borrowedBooks) {


                contentWriter.write(a.getItemID() + " " + a.getLoanDate().get(Calendar.YEAR) + " " + a.getLoanDate().get(Calendar.MONTH) + " " + a.getLoanDate().get(Calendar.DATE));

                contentWriter.newLine();
            }

        } catch (IOException e) {

        }

        try {
            contentWriter.close();

        } catch (IOException e) {
            System.out.println("could not close 1");
        }
        try {
            accountFileWriter.close();
        } catch (IOException e) {
            System.out.println("could not close 2");
        }

    }

    public void borrowBook(BookItem bookItem) {


        if (bookItem.getState().isLoanable()) {
            bookItem.loan();
            borrowedBooks.add(bookItem);

        }

    }

    public void borrowBook(BookItem bookItem, int year, int month, int dayOfMonth) {


        if (bookItem.getState().isLoanable()) {
            bookItem.loan(year, month, dayOfMonth);
            borrowedBooks.add(bookItem);

        }

    }

    public ArrayList<BookItem> getBorrowedBooks() {

        return borrowedBooks;

    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Library getLibrary() {

        return library;

    }

}
