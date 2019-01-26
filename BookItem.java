package se_project;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BookItem {

    private Calendar loanDate;
    private Calendar dueDate;
    private Book book;

    public String getItemID() {
        return itemID;
    }

    private String itemID;
    private BookItemState state;

    public BookItem(String itemID, Book book, BookItemState state) {
        this.itemID = itemID;
        this.state = state;
        this.book = book;

        
    }

    public void loan() {

        state = BookItemState.LOANED;
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        loanDate = new GregorianCalendar();
        dueDate = new GregorianCalendar();
        dueDate.add(Calendar.WEEK_OF_YEAR, 1);

    }

    public void loan(int year, int month, int dayOfMonth) {

        state = BookItemState.LOANED;
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        loanDate = new GregorianCalendar(year, month, dayOfMonth);
        dueDate = new GregorianCalendar(year, month, dayOfMonth);
        dueDate.add(Calendar.WEEK_OF_YEAR, 1);

    }

    public Book getBookDetails() {
        return book;

    }

    public BookItemState getState() {
        return state;
    }

    public Calendar getLoanDate() {
        return loanDate;
    }

    public Calendar getDueDate() {

        return dueDate;
    }

}
