package se_project;

import java.util.ArrayList;
import java.util.Arrays;

public class Student extends Account {

    private String specialWord;
    private double balance;

    public Student(String username, String password, Library library, String specialWord, double balance) {
        super(username, password, library);
        this.specialWord = specialWord;
        this.balance = balance;
    }

    public String getSpecialWord() {
        return specialWord;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
