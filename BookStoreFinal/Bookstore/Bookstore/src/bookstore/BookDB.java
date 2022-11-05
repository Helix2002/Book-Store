package bookstore;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class BookDB {

    private String filename;
    private static BookDB instance = null;

    private BookDB(String n) {
        filename = n;
    }

    public static BookDB getInstance() {
        if (instance == null) {
            instance = new BookDB("books.txt");
        }

        return instance;
    }

    public boolean verifyBook(String title) {
        ArrayList<Book> books = getBooks();

        for (Book b : books) {
            if (b.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String[] book = scan.nextLine().split(" ", 2);
                int price = Integer.parseInt(book[0]);
                String title = book[1];
                books.add(new Book(title, price));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return books;
    }

    public void overwriteFile(ArrayList<Book> books) {
        try {
            FileWriter fw = new FileWriter(filename);

            for (Book b : books) {
                fw.write(b.toString() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
