package bookstore;

import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        String userSignedin = "tim123";
        
        CustomerDB fr = CustomerDB.getInstance();
        fr.setUserSignedin(userSignedin);
        ArrayList<Customer> customers;
        customers = fr.getCustomers();
        for(Customer c: customers) {
            System.out.println(c);
        }
        
        System.out.println("\n");
        
        for(Customer c: customers) {
            if(!c.getUsername().equals("world")) continue;
        }
        
        fr.overwriteFile(customers);
        
        BookDB br = BookDB.getInstance();
        ArrayList<Book> books = br.getBooks();
        for(Book b: books) {
            System.out.println(b);
        }
        
        
    }
}
