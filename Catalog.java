package se_project;

import java.util.ArrayList;

public class Catalog {

    private String faculty;
    private ArrayList<Book> books;

    public Catalog(String faculty) {

        this.faculty = faculty;
        books = new ArrayList<Book>();

    }

    public String getFaculty() {
        return faculty;
    }

    public void addBook(Book book) {

        for (Book a : books) {

            if (a.getTitle().equals(book.getTitle())) {
                return;
            }

        }

        books.add(book);

    }

    public void removeBook(String title) {

        for (Book a : books) {

            if (a.getTitle().equals(title)) {
                books.remove(a);
            }

        }

    }

    public Book getBook(String title) {

        for (Book a : books) {

            if (a.getTitle().equals(title)) {
                return a;
            }

        }

        return null;

    }

    public ArrayList<Book> getBooks() {
        return books;
    }

}
