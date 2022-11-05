package bookstore;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDB {

    private String filename;
    private String userSignedin = null;
    private int points = 0;
    private static CustomerDB instance = null;

    private CustomerDB(String filename) {
        this.filename = filename;
    }

    public static CustomerDB getInstance() {
        if (instance == null) {
            instance = new CustomerDB("customers.txt");
        }
        return instance;
    }

    public boolean verifyCustomer(String username, String password) {
        ArrayList<Customer> customers = getCustomers();

        for (Customer c : customers) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomer() {

        ArrayList<Customer> customers = getCustomers();

        for (Customer c : customers) {
            if (c.getUsername().equals(getuserSignedin())) {
                return c;

            }
        }

        return null;
    }

    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String[] customer = scan.nextLine().split(" ");
                String username = customer[0];
                String password = customer[1];
                int points = Integer.parseInt(customer[2]);
                customers.add(new Customer(username, password, points));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return customers;
    }

    public void overwriteFile(ArrayList<Customer> customers) {
        try {
            FileWriter fw = new FileWriter(filename);

            for (Customer c : customers) {
                fw.write(c.toString() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void addPointsToCustomer(int points) {
        ArrayList<Customer> customers = getCustomers();

        for (Customer c : customers) {
            if (!c.getUsername().equals(userSignedin)) {
                continue;
            }
            c.addPoints(points);
            overwriteFile(customers);
        }
    }
    
    public void deductPointsFromtCustomer(int points) {
        ArrayList<Customer> customers = getCustomers();

        for (Customer c : customers) {
            if (!c.getUsername().equals(userSignedin)) {
                continue;
            }
            c.deductPoints(points);
            overwriteFile(customers);
        }
    }
    
    public void setPointsOfCustomer(int points) {
        ArrayList<Customer> customers = getCustomers();

        for (Customer c : customers) {
            if (!c.getUsername().equals(userSignedin)) {
                continue;
            }
            c.setPoints(points);
            overwriteFile(customers);
        }
    }

    public String getuserSignedin() {
        return userSignedin;
    }

    public void setUserSignedin(String userSignedin) {
        this.userSignedin = userSignedin;
    }

}
