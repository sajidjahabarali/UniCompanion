package se_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Library {

    private ArrayList<Catalog> catalogs;
    private ArrayList<BookItem> bookCopies;

    public Library() {

        catalogs = new ArrayList<Catalog>();
        bookCopies = new ArrayList<BookItem>();

        this.addCatalog(new Catalog("Computer Science"));
        this.addCatalog(new Catalog("Fiction"));

        this.getCatalog("Computer Science").addBook(new Book("Software Engineering", "Mustafa Bozkurt", "SEMB"));
        this.getCatalog("Computer Science").getBook("Software Engineering").addCopy("01");
        this.getCatalog("Computer Science").getBook("Software Engineering").addCopy("06");
        this.getCatalog("Computer Science").getBook("Software Engineering").addCopy("07");

        this.getCatalog("Computer Science").addBook(new Book("Logic and Discrete Structures", "Soren Riis", "LDSSR"));
        this.getCatalog("Computer Science").getBook("Logic and Discrete Structures").addCopy("02");

        this.getCatalog("Computer Science").addBook(new Book("Procedural Programming", "Paul Curzon", "PPPC"));
        this.getCatalog("Computer Science").getBook("Procedural Programming").addCopy("03");
        this.getCatalog("Computer Science").getBook("Procedural Programming").addCopy("08");

        this.getCatalog("Fiction").addBook(new Book("Harry Potter and the Philosophers Stone", "JK Rowling", "HPPSJKR"));
        this.getCatalog("Fiction").getBook("Harry Potter and the Philosophers Stone").addCopy("04");

        this.getCatalog("Fiction").addBook(new Book("Harry Potter and the Prisoner of Azkaban", "JK Rowling", "HPPAJKR"));
        this.getCatalog("Fiction").getBook("Harry Potter and the Prisoner of Azkaban").addCopy("05");

        initBookCopies();

    }

    private void initBookCopies() {

        for (Catalog a : catalogs) {

            for (Book b : a.getBooks()) {

                for (BookItem c : b.getCopies()) {
                    bookCopies.add(c);
                }

            }

        }
    }

    public BookItem getCopy(String id) {

        for (BookItem a : bookCopies) {

            if (a.getItemID().equals(id)) {
                return a;
            }

        }

        System.out.println("Get copy error in library class");
        return null;

    }

    public void addCatalog(Catalog catalog) {

        for (Catalog a : catalogs) {

            if (a.getFaculty().equals(catalog.getFaculty())) {
                return;
            }

        }

        catalogs.add(catalog);
    }

    public void removeCatalog(String faculty) {

        for (Catalog a : catalogs) {

            if (a.getFaculty().equals(faculty)) {
                catalogs.remove(a);
            }

        }

    }

    public Catalog getCatalog(String faculty) {

        for (Catalog a : catalogs) {

            if (a.getFaculty().equals(faculty)) {
                return a;
            }

        }

        return null;

    }

    public ArrayList<Catalog> getCatalogs() {
        return catalogs;
    }

}
