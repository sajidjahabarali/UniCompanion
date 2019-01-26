package se_project;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountRecord {

    private ArrayList<Account> accounts;
    private FileWriter accountRecordFileWriter;
    private File accountRecordFile;
    private FileReader accountRecordFileReader;
    private String[] accountDetails;
    private Account currentAccount;
    private Library library;
    private File accountFile;
    private FileReader accountFileReader;
    private Scanner contentReader;
    private FileWriter accountFileWriter;
    private BufferedWriter contentWriter;
    private String[] currentBookDetails;

    public AccountRecord() {

        accounts = new ArrayList<Account>();
        library = new Library();
        initLibrary();

    }

    public void initLibrary() {

        for (Account a : accounts) {
            try {

                accountFileReader = new FileReader(a.getUsername() + ".txt");

                contentReader = new Scanner(accountFileReader);

                while (contentReader.hasNextLine()) {
                    currentBookDetails = contentReader.nextLine().split(" ");

                    a.borrowBook(library.getCopy(currentBookDetails[0]), Integer.parseInt(currentBookDetails[1]), Integer.parseInt(currentBookDetails[2]), Integer.parseInt(currentBookDetails[3]));

                }

                try {
                    accountFileReader.close();
                } catch (IOException e) {

                }
                contentReader.close();

            } catch (FileNotFoundException e) {

                accountFile = new File(a.getUsername() + ".txt");

            }
        }
    }

    public boolean addAccount(String username, String password, char type) {

        for (Account a : accounts) {

            if (a.getUsername().equals(username)) {
                return false;
            }
        }

        if (type == 'a') {
            accounts.add(new Administrator(username, password, library, this));
        } else if (type == 's') {
            accounts.add(new Student(username, password, library, "special", 0));
        } else if (type == 'l') {
            accounts.add(new Lecturer(username, password, library));
        } else {
            return false;
        }
        return true;
    }

    public boolean removeAccount(String username) {

        for (Account a : accounts) {

            if (a.getUsername().equals(username)) {
                accounts.remove(a);

                return true;
            }
        }

        return false;
    }

    public boolean verifyAccount(String username, String password) {


        for (Account a : accounts) {

            if (a.getUsername().equals(username)) {

                if (a.getPassword().equals(password)) {

                    return true;
                }


                return false;
            }
        }

        return false;
    }

    public Account getAccount(String username) {
        for (Account a : accounts) {

            if (a.getUsername().equals(username)) {
                return a;

            }
        }


        return null;
    }

    public void update() {

        try {

            accountRecordFileWriter = new FileWriter("AccountRecord.txt");
            contentWriter = new BufferedWriter(accountRecordFileWriter);

            for (Account a : accounts) {

                if (a instanceof Administrator) {
                    try {

                        contentWriter.write("A ");
                    } catch (IOException e) {

                    }

                } else if (a instanceof Student) {
                    try {

                        contentWriter.write("S ");
                    } catch (IOException e) {

                    }
                } else {
                    try {

                        contentWriter.write("L ");
                    } catch (IOException e) {

                    }
                }

                contentWriter.write(a.getUsername() + " " + a.getPassword() + " ");

                if (a instanceof Student) {
                    contentWriter.write(((Student) a).getSpecialWord() + " " + ((Student) a).getBalance());
                }
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
            accountRecordFileWriter.close();
        } catch (IOException e) {
            System.out.println("could not close 2");
        }

    }

    public void load() {

        try {

            accountRecordFileReader = new FileReader("AccountRecord.txt");
            contentReader = new Scanner(accountRecordFileReader);

            while (contentReader.hasNextLine()) {
                accountDetails = contentReader.nextLine().split(" ");

                if (accountDetails[0].equals("A")) {
                    currentAccount = new Administrator(accountDetails[1], accountDetails[2], library, this);
                    accounts.add(currentAccount);
                } else if (accountDetails[0].equals("S")) {
                    try {
                        currentAccount = new Student(accountDetails[1], accountDetails[2], library, accountDetails[3], Double.parseDouble(accountDetails[4]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        currentAccount = new Student(accountDetails[1], accountDetails[2], library, "special", 0);
                    }
                    accounts.add(currentAccount);
                } else if (accountDetails[0].equals("L")) {
                    currentAccount = new Lecturer(accountDetails[1], accountDetails[2], library);
                    accounts.add(currentAccount);
                } else {
                    System.out.println("account type error");
                }

            }


            try {
                accountRecordFileReader.close();
            } catch (IOException e) {

            }
            contentReader.close();

        } catch (FileNotFoundException e) {

            accountRecordFile = new File("AccountRecord.txt");
            try {
                accountRecordFile.createNewFile();

                accounts.add(new Administrator("admin", "admin", library, this));
                accounts.add(new Student("student", "student", library, "special", 0));
                accounts.add(new Lecturer("lecturer", "lecturer", library));

            } catch (IOException e2) {
                System.out.println("File not created");
            }

        }

    }

    public void clear() {
        accounts = new ArrayList<Account>();
    }
}
