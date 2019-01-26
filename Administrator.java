package se_project;

public class Administrator extends Account {

    private AccountRecord accountRecord;

    public Administrator(String username, String password, Library library, AccountRecord accountRecord) {
        super(username, password, library);
        this.accountRecord = accountRecord;
    }

    public boolean addAccount(String username, String password) {

        return false;
    }

    public boolean removeAccount(String username) {

        return false;
    }

    public boolean changeAccountPassword(String username) {

        return false;
    }

    public AccountRecord getAccountRecord() {
        return accountRecord;
    }
}
