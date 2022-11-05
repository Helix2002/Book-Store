package bookstore;

import java.util.ArrayList;
import java.util.Iterator;

public class Admin {

    private static Admin instance = null;

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public void addCustomer(Customer c) {
        ArrayList<Customer> customers = CustomerDB.getInstance().getCustomers();
        customers.add(c);
        CustomerDB.getInstance().overwriteFile(customers);
    }

    public void delCustomer(Customer customer) {
        ArrayList<Customer> customers = CustomerDB.getInstance().getCustomers();

        Iterator<Customer> iter = customers.iterator();

        while (iter.hasNext()) {
            if (iter.next().getUsername().equals(customer.getUsername())) {
                iter.remove();
            }
        }

        CustomerDB.getInstance().overwriteFile(customers);
    }

    public void addBook(Book b) {
        ArrayList<Book> books = BookDB.getInstance().getBooks();
        books.add(b);
        BookDB.getInstance().overwriteFile(books);
    }

    public void delBook(Book book) {
        ArrayList<Book> books = BookDB.getInstance().getBooks();

        Iterator<Book> iter = books.iterator();

        while (iter.hasNext()) {
            if (iter.next().getTitle().equals(book.getTitle())) {
                iter.remove();
            }
        }

        BookDB.getInstance().overwriteFile(books);
    }
}
