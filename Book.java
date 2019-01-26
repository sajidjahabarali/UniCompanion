package se_project;

import java.util.ArrayList;

public class Book {

    private ArrayList<BookItem> copies;
    private String title;
    private String author;
    private String isbn;
    private int availableQuantity;

    public Book(String title, String author, String isbn) {

        this.isbn = isbn;
        this.author = author;
        this.title = title;
        copies = new ArrayList<BookItem>();
        availableQuantity = copies.size();

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        if (this.title == null) {
            this.title = title;
        }

    }

    public void setAuthor(String author) {

        if (this.author == null) {
            this.author = author;
        }

    }

    public void setIsbn(String isbn) {

        if (this.isbn == null) {
            this.isbn = isbn;
        }
    }

    public void addCopy(String id) {

        copies.add(new BookItem(id, this, BookItemState.AVAILABLE));
        availableQuantity++;

    }

    public int getQuantity() {

        return copies.size();

    }

    public int getAvailableQuantity() {

        return availableQuantity;

    }

    public void setAvailableQuantity(int a) {

        availableQuantity = a;

    }

    public BookItem getCopy() {

        for (BookItem a : copies) {

            if (a.getState() == BookItemState.AVAILABLE) {
                return a;
            }

        }

        System.out.println("Get copy error");
        return null;

    }

    public ArrayList<BookItem> getCopies() {

        return copies;
    }

    public boolean isAvailable() {

        if (availableQuantity > 0) {
            return true;
        } else {

            return false;
        }

    }
}
