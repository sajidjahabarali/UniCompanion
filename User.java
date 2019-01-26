package se_project;

import java.io.FileNotFoundException;
import java.io.IOException;

public class User {

    private String username;
    private String password;
    private AccountRecord accountRecord;

    public User(String username, String password) {

        accountRecord = new AccountRecord();
        accountRecord.load();
        accountRecord.initLibrary();

        this.username = username;
        this.password = password;

    }

    public AccountRecord getAccountRecord() {
        return accountRecord;
    }

    public boolean login() {

        if (accountRecord.verifyAccount(username, password)) {

            return true;
        }

        return false;

    }

    public String getUserType(String username) {

        if (accountRecord.getAccount(username) instanceof Student) {

            return "Student";
        } else if (accountRecord.getAccount(username) instanceof Lecturer) {

            return "Lecturer";
        } else if (accountRecord.getAccount(username) instanceof Administrator) {

            return "Administrator";
        } else {
            System.out.println("getUserType error");
            return null;
        }

    }

    public Account getAccount() {

        return accountRecord.getAccount(username);
    }

}
