package bookstore;

import java.awt.Checkbox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Jasper
 */
public class ApplicationGUI extends Application {

    // Obtain instance of Admin, BookDB, and CustomerDB
    Admin owner = Admin.getInstance();
    BookDB bkDB = BookDB.getInstance();
    CustomerDB custDB = CustomerDB.getInstance();

    // Add Book & Customer database to ObeervableList for TableView
    private TableView<Book> bkTb = new TableView();
    private ObservableList<Book> bkList
            = FXCollections.observableArrayList(
                    bkDB.getBooks()
            );

    private TableView<Customer> custTb = new TableView();
    private ObservableList<Customer> custList
            = FXCollections.observableArrayList(
                    custDB.getCustomers()
            );

    private TableView<Book> buybkTb = new TableView();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bookstore App"); // Set title of window
        primaryStage.setResizable(false); // Disable user from resizing window

        // Initialize scenes
        Scene loginScreen, ownerScreen, customerStartScreen;
        Scene ownerBookScreen, ownerCustomerScreen;
        Scene customerCostScreen;

        AnchorPane a1 = new AnchorPane();
        loginScreen = new Scene(a1, 500, 350);

        AnchorPane a2 = new AnchorPane();
        ownerScreen = new Scene(a2, 375, 400);
        AnchorPane a3 = new AnchorPane();
        ownerBookScreen = new Scene(a3, 650, 475);
        AnchorPane a4 = new AnchorPane();
        ownerCustomerScreen = new Scene(a4, 650, 475);

        AnchorPane a5 = new AnchorPane();
        customerStartScreen = new Scene(a5, 650, 475);
        AnchorPane a6 = new AnchorPane();
        customerCostScreen = new Scene(a6, 375, 400);

        /* 
           Scene 1: Login Screen
           User enters username and password. If username and password is 
           "admin", scene switches to Scene 2: Owner Screen. Otherwise, scene
           switches to Scene 3: Customer Start Screen.
         */
        // Title 
        Text title = new Text();
        title.setX(65);
        title.setY(75);
        title.setText("Welcome to the Bookstore Application");
        title.setStyle("-fx-font: 18 system;");

        // Username and Password Texts
        Text t1 = new Text();
        t1.setX(65);
        t1.setY(115);
        t1.setText("Username:");
        t1.setStyle("-fx-font: 16 system; -fx-font-weight: bold");

        Text t2 = new Text();
        t2.setText("Password:");
        t2.setX(65);
        t2.setY(180);
        t2.setStyle("-fx-font: 16 system; -fx-font-weight: bold");

        // Username and Password TextFields
        TextField un = new TextField();
        un.setTranslateX(65);
        un.setTranslateY(120);
        un.setPrefWidth(370);
        un.setPrefHeight(30);
        un.setPromptText("Enter username");
        un.setStyle("-fx-font: 14 system;");

        PasswordField pw = new PasswordField(); // Mask password
        pw.setTranslateX(65);
        pw.setTranslateY(185);
        pw.setPrefWidth(370);
        pw.setPrefHeight(30);
        pw.setPromptText("Enter password");
        pw.setStyle("-fx-font: 14 system;");

        TextField showpw = new TextField(); // Unmask password
        showpw.setTranslateX(65);
        showpw.setTranslateY(185);
        showpw.setPrefWidth(370);
        showpw.setPrefHeight(30);
        showpw.setPromptText("Enter password");
        showpw.setStyle("-fx-font: 14 system;");
        showpw.setVisible(false);

        CheckBox cbox = new CheckBox("Show Password");
        cbox.setTranslateX(314);
        cbox.setTranslateY(220);
        cbox.setStyle("-fx-font: 14 system;");

        final Label msg = new Label("");
        msg.setTranslateX(158);
        msg.setTranslateY(320);
        msg.setStyle("-fx-font: 14 system;-fx-text-fill: red;");

        // Login Button
        Button login = new Button();
        login.setTranslateX(65);
        login.setTranslateY(260);
        login.setPrefWidth(370);
        login.setPrefHeight(50);
        login.setText("Login");
        login.setStyle("-fx-font: 18 system;");

        a1.getChildren().addAll(title, t1, t2, un, pw, showpw, cbox, login, msg);
        primaryStage.setScene(loginScreen);
        primaryStage.show();

        /* 
           Scene 2: Owner Screen
           Displays Books Button, Customers Button, and Logout Button
         */
        // Books Button
        Button bookBtn = new Button();
        bookBtn.setTranslateX(115);
        bookBtn.setTranslateY(50);
        bookBtn.setPrefWidth(170);
        bookBtn.setPrefHeight(50);
        bookBtn.setText("Books");
        bookBtn.setStyle("-fx-font: 14 system;");

        // Customers Button
        Button custBtn = new Button();
        custBtn.setTranslateX(115);
        custBtn.setTranslateY(125);
        custBtn.setPrefWidth(170);
        custBtn.setPrefHeight(50);
        custBtn.setText("Customers");
        custBtn.setStyle("-fx-font: 14 system;");

        // Logout Button
        Button logout = new Button();
        logout.setTranslateX(115);
        logout.setTranslateY(300);
        logout.setPrefWidth(170);
        logout.setPrefHeight(50);
        logout.setText("Logout");
        logout.setStyle("-fx-font: 14 system;");

        a2.getChildren().addAll(bookBtn, custBtn, logout);

        /* 
           Scene 2.1: Owner Book Screen
           Top Part: A table with book name and book price
           Middle Part: Book name and price textfields and Add Button
           Bottom Part: Delete Button and Back Button
         */
        // Book Title
        Text bkTitle = new Text();
        bkTitle.setX(43);
        bkTitle.setY(50);
        bkTitle.setText("Book Database");
        bkTitle.setStyle("-fx-font: 18 system;");

        // Book Table
        bkTb.setTranslateX(43);
        bkTb.setTranslateY(63);
        bkTb.setPrefWidth(565);
        bkTb.setPrefHeight(270);
        bkTb.setEditable(false);

        TableColumn bkNameCol = new TableColumn("Book Name");
        bkNameCol.setMinWidth(423);
        bkNameCol.setMaxWidth(423);
        bkNameCol.setCellValueFactory(
                new PropertyValueFactory<>("title"));

        TableColumn bkPriceCol = new TableColumn("Book Price");
        bkPriceCol.setMinWidth(141);
        bkPriceCol.setMaxWidth(141);
        bkPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        bkTb.setItems(bkList);
        bkTb.getColumns().addAll(bkNameCol, bkPriceCol);

        // Book Name and Price Texts
        Text t3 = new Text();
        t3.setX(43);
        t3.setY(392);
        t3.setText("Name:");
        t3.setStyle("-fx-font: 14 system; -fx-font-weight: bold");

        Text t4 = new Text();
        t4.setX(264);
        t4.setY(392);
        t4.setText("Price:");
        t4.setStyle("-fx-font: 14 system; -fx-font-weight: bold");

        // Book Name and Price TextFields
        TextField bkName = new TextField();
        bkName.setTranslateX(91);
        bkName.setTranslateY(372);
        bkName.setPrefWidth(150);
        bkName.setPrefHeight(30);
        bkName.setPromptText("Enter book name");
        bkName.setStyle("-fx-font: 12 system;");

        TextField bkPrice = new TextField();
        bkPrice.setTranslateX(313);
        bkPrice.setTranslateY(372);
        bkPrice.setPrefWidth(150);
        bkPrice.setPrefHeight(30);
        bkPrice.setPromptText("Enter book price");
        bkPrice.setStyle("-fx-font: 12 system;");

        Label msg2 = new Label("");
        msg2.setTranslateX(43);
        msg2.setTranslateY(343);
        msg2.setStyle("-fx-font: 14 system;-fx-text-fill: red;");

        // Add Button
        Button addBk = new Button();
        addBk.setTranslateX(491);
        addBk.setTranslateY(372);
        addBk.setPrefWidth(116);
        addBk.setPrefHeight(19);
        addBk.setText("Add");
        addBk.setStyle("-fx-font:14 system;");

        // Delete Button
        Button delBk = new Button();
        delBk.setTranslateX(171);
        delBk.setTranslateY(416);
        delBk.setPrefWidth(116);
        delBk.setPrefHeight(30);
        delBk.setText("Delete");
        delBk.setStyle("-fx-font:14 system;");

        // Back Button
        Button backBk = new Button();
        backBk.setTranslateX(366);
        backBk.setTranslateY(416);
        backBk.setPrefWidth(116);
        backBk.setPrefHeight(30);
        backBk.setText("Back");
        backBk.setStyle("-fx-font:14 system;");

        a3.getChildren().addAll(bkTitle, t3, t4, bkTb, bkName, bkPrice, addBk, delBk, backBk, msg2);

        /* 
           Scene 2.2: Owner Customer Screen
           Top Part: A table with customer name, password, and points
           Middle Part: Username and password textfields and Add button
           Bottom Part: Delete Button and Back Button
         */
        // Customer Title
        Text custTitle = new Text();
        custTitle.setX(43);
        custTitle.setY(50);
        custTitle.setText("Customer Database");
        custTitle.setStyle("-fx-font: 18 system;");

        // Customer Table
        custTb.setTranslateX(43);
        custTb.setTranslateY(63);
        custTb.setPrefWidth(565);
        custTb.setPrefHeight(270);
        custTb.setEditable(false);

        TableColumn custNameCol = new TableColumn("Username");
        custNameCol.setMinWidth(282);
        custNameCol.setMaxWidth(282);
        custNameCol.setCellValueFactory(
                new PropertyValueFactory<>("username"));

        TableColumn custPassCol = new TableColumn("Password");
        custPassCol.setMinWidth(171);
        custPassCol.setMaxWidth(171);
        custPassCol.setCellValueFactory(
                new PropertyValueFactory<>("password"));

        TableColumn custPointCol = new TableColumn("Points");
        custPointCol.setMinWidth(110);
        custPointCol.setMaxWidth(110);
        custPointCol.setCellValueFactory(
                new PropertyValueFactory<>("points"));

        custTb.setItems(custList);
        custTb.getColumns().addAll(custNameCol, custPassCol, custPointCol);

        // Customer Username and Password Texts
        Text t5 = new Text();
        t5.setX(43);
        t5.setY(392);
        t5.setText("Username:");
        t5.setStyle("-fx-font: 14 system; -fx-font-weight: bold");

        Text t6 = new Text();
        t6.setX(264);
        t6.setY(392);
        t6.setText("Password:");
        t6.setStyle("-fx-font: 14 system; -fx-font-weight: bold");

        // Book Name and Price TextFields
        TextField custName = new TextField();
        custName.setTranslateX(125);
        custName.setTranslateY(372);
        custName.setPrefWidth(122);
        custName.setPrefHeight(30);
        custName.setPromptText("Enter username");
        custName.setStyle("-fx-font: 12 system;");

        TextField custPass = new TextField();
        custPass.setTranslateX(347);
        custPass.setTranslateY(372);
        custPass.setPrefWidth(122);
        custPass.setPrefHeight(30);
        custPass.setPromptText("Enter password");
        custPass.setStyle("-fx-font: 12 system;");

        Label msg3 = new Label("");
        msg3.setTranslateX(43);
        msg3.setTranslateY(343);
        msg3.setMinWidth(325);
        msg3.setStyle("-fx-font: 14 system;-fx-text-fill: red;");

        // Add Button
        Button addCust = new Button();
        addCust.setTranslateX(491);
        addCust.setTranslateY(372);
        addCust.setPrefWidth(116);
        addCust.setPrefHeight(19);
        addCust.setText("Add");
        addCust.setStyle("-fx-font:14 system;");

        // Delete Button
        Button delCust = new Button();
        delCust.setTranslateX(171);
        delCust.setTranslateY(416);
        delCust.setPrefWidth(116);
        delCust.setPrefHeight(30);
        delCust.setText("Delete");
        delCust.setStyle("-fx-font:14 system;");

        // Back Button
        Button backCust = new Button();
        backCust.setTranslateX(366);
        backCust.setTranslateY(416);
        backCust.setPrefWidth(116);
        backCust.setPrefHeight(30);
        backCust.setText("Back");
        backBk.setStyle("-fx-font:14 system;");

        a4.getChildren().addAll(custTitle, t5, t6, custTb, custName, custPass, addCust, delCust, backCust, msg3);

        /* 
           Scene 3: Customer Start Screen
           Top Part: Welcome customerName. You have P points. Your status is S.
           Middle Part: A table with book name, book price, and select
           Bottom Part: Buy Button, Redeem Points and Buy Button, and Logout Button
         */
        // Customer Start Title
        Text startTitle = new Text();
        startTitle.setX(43);
        startTitle.setY(50);
        startTitle.setStyle("-fx-font: 18 system;");

        // Buy Book Table
        buybkTb.setTranslateX(43);
        buybkTb.setTranslateY(81);
        buybkTb.setPrefWidth(565);
        buybkTb.setPrefHeight(284);
        buybkTb.setEditable(false);

        TableColumn buybkNameCol = new TableColumn("Book Name");
        buybkNameCol.setMinWidth(334);
        buybkNameCol.setMaxWidth(334);
        buybkNameCol.setCellValueFactory(
                new PropertyValueFactory<>("title"));

        TableColumn buybkPriceCol = new TableColumn("Book Price");
        buybkPriceCol.setMinWidth(139);
        buybkPriceCol.setMaxWidth(139);
        buybkPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        TableColumn selectCol = new TableColumn("Select");
        selectCol.setMinWidth(91);
        selectCol.setMaxWidth(91);
        selectCol.setStyle("-fx-alignment: center;");
        selectCol.setCellValueFactory(
                new PropertyValueFactory<>("select"));

        buybkTb.setItems(bkList);
        buybkTb.getColumns().addAll(buybkNameCol, buybkPriceCol, selectCol);

        // Buy Book Button
        Button buyBk = new Button();
        buyBk.setTranslateX(43);
        buyBk.setTranslateY(416);
        buyBk.setPrefWidth(116);
        buyBk.setPrefHeight(30);
        buyBk.setText("Buy");
        buyBk.setStyle("-fx-font:14 system;");

        // Buy Book with Points Button
        Button buyBkPts = new Button();
        buyBkPts.setTranslateX(234);
        buyBkPts.setTranslateY(416);
        buyBkPts.setPrefWidth(184);
        buyBkPts.setPrefHeight(30);
        buyBkPts.setText("Redeem Points and Buy");
        buyBkPts.setStyle("-fx-font:14 system;");

        // Logout Button
        Button custLogout1 = new Button();
        custLogout1.setTranslateX(491);
        custLogout1.setTranslateY(416);
        custLogout1.setPrefWidth(116);
        custLogout1.setPrefHeight(30);
        custLogout1.setText("Logout");
        custLogout1.setStyle("-fx-font:14 system;");

        final Label msg4 = new Label("");
        msg4.setTranslateX(43);
        msg4.setTranslateY(375);
        msg4.setStyle("-fx-font: 14 system;-fx-text-fill: red;");

        a5.getChildren().addAll(startTitle, buybkTb, buyBk, buyBkPts, custLogout1, msg4);

        /* 
           Scene 3.1: Customer Cost Screen
           Top Part: Displays total cost of all the selected books
           Middle Part: Display point and status of customer
           Bottom Part: Logout Button
         */
        // Total Cost Text
        Text tc = new Text();
        tc.setX(131);
        tc.setY(75);
        tc.setText("Total Cost: TC");
        tc.setStyle("-fx-font: 18 system;");

        // Points Text
        Text pt = new Text();
        pt.setX(67);
        pt.setY(125);
        pt.setText("Points: P");
        pt.setStyle("-fx-font: 18 system;");

        // Status Text
        Text st = new Text();
        st.setX(235);
        st.setY(125);
        st.setText("Status: S");
        st.setStyle("-fx-font: 18 system;");

        // Logout Button
        Button custLogout2 = new Button();
        custLogout2.setTranslateX(103);
        custLogout2.setTranslateY(296);
        custLogout2.setPrefWidth(170);
        custLogout2.setPrefHeight(50);
        custLogout2.setText("Logout");
        custLogout2.setStyle("-fx-font:14 system;");

        /*
            EventHandler
         */
        // EventHandler: When Show Password is checked
        cbox.setOnAction((ActionEvent event) -> {
            if (cbox.isSelected()) {
                showpw.setText(pw.getText());
                showpw.setVisible(true);
                pw.setVisible(false);
                return;
            }
            pw.setText(showpw.getText());
            pw.setVisible(true);
            showpw.setVisible(false);
        });

        // EventHandler: When login button is clicked
        login.setOnAction((ActionEvent event) -> {
            if (pw.getText().isEmpty()) {
                pw.setText(showpw.getText());
            }
            if (un.getText().equals("admin") && pw.getText().equals("admin")) {
                // Move to Scene 2: Owner Screen
                bkList = FXCollections.observableArrayList(bkDB.getBooks());
                custList = FXCollections.observableArrayList(custDB.getCustomers());
                bkTb.setItems(bkList);
                custTb.setItems(custList);

                primaryStage.setScene(ownerScreen);
                msg.setText("");
            } else {
                // Move to Scene 3: Customer Start Screen
                // Check if username and password matches CustomerDB 
                boolean validCustomer = custDB.verifyCustomer(un.getText(), pw.getText());

                if (validCustomer) {
                    // Valid username and password
                    custDB.setUserSignedin(un.getText());
                    bkList = FXCollections.observableArrayList(bkDB.getBooks());
                    buybkTb.setItems(bkList);

                    primaryStage.setScene(customerStartScreen);
                    Customer cust = custDB.getCustomer();
                    startTitle.setText("Welcome " + cust.getUsername()
                            + ". You have " + cust.getPoints()
                            + " points. Your status is " + cust.getStatus());
                    msg.setText("");
                } else {
                    // Invalid username and password
                    msg.setText("Invalid username or password");
                }
            }
            // Clear TextFields
            un.clear();
            pw.clear();
            showpw.clear();
        });

        // EventHandler: When books button is clicked
        bookBtn.setOnAction((ActionEvent event) -> {
            // Move to Scene 2.1: Owner Book Screen
            primaryStage.setScene(ownerBookScreen);
        });

        // EventHandler: When books button is clicked
        custBtn.setOnAction((ActionEvent event) -> {
            // Move to Scene 2.2: Owner Customer Screen
            primaryStage.setScene(ownerCustomerScreen);
        });

        // EventHandler: When logout button is clicked
        logout.setOnAction((ActionEvent event) -> {
            // Move to Scene 1: Login Screen
            primaryStage.setScene(loginScreen);
        });

        // EventHandler: When logout button is clicked
        custLogout2.setOnAction((ActionEvent event) -> {
            // Move to Scene 1: Login Screen
            primaryStage.setScene(loginScreen);
        });

        // EventHandler: When add book button is clicked
        addBk.setOnAction((ActionEvent event) -> {
            if (bkName.getText().isEmpty() || bkPrice.getText().isEmpty()) {
                // When bkName or bkPrice textfields empty
                msg2.setText("Error: Book name or price is empty");
            } else {
                boolean duplicateBk = bkDB.verifyBook(bkName.getText());

                try {
                    if (!duplicateBk) {
                        Book newBk = new Book(
                                bkName.getText(),
                                Integer.parseInt(bkPrice.getText()));
                        owner.addBook(newBk);
                        bkList.add(newBk);
                        msg2.setText("");
                    } else {
                        msg2.setText("Error: Book already exists");
                    }
                } catch (NumberFormatException e) {
                    msg2.setText("Error: Book price is invalid");
                }
            }
            // Clear textfields
            bkName.clear();
            bkPrice.clear();
        });

        // EventHandler: When delete book button is clicked
        delBk.setOnAction((ActionEvent event) -> {
            try {
                // Select a row in table and delete from bkTb and bkDB
                Book bk = bkTb.getSelectionModel().getSelectedItem();

                bkTb.getItems().removeAll(bk);
                owner.delBook(bk);
                msg2.setText("");
            } catch (NullPointerException npe) {
                msg2.setText("Error: No book is selected");
            }
        });

        // EventHandler: When back button is clicked
        backBk.setOnAction((ActionEvent event) -> {
            // Move to Scene 2: OwnerScreen
            primaryStage.setScene(ownerScreen);
            msg2.setText("");
        });

        // EventHandler: When add customer button is clicked
        addCust.setOnAction((ActionEvent event) -> {
            if (custName.getText().isEmpty() || custPass.getText().isEmpty()) {
                // When custName or custPass textfields empty
                msg3.setText("Error: Username or password is empty");
            } else if (custName.getText().equals("admin") || custPass.getText().equals("admin")) {
                // When custName or custPass is "admin"
                msg3.setText("Error: Username or password can't be admin");
            } else if (custName.getText().contains(" ")) {
                // When username has a space
                msg3.setText("Error: Username can't contain any spaces");
            } else {
                // Check for username duplicates
                boolean duplicateCust = false;
                ArrayList<Customer> customers = custDB.getCustomers();
                for (Customer c : customers) {
                    if (c.getUsername().equals(custName.getText())) {
                        duplicateCust = true;
                    }
                }
                if (!duplicateCust) {
                    Customer newCust = new Customer(
                            custName.getText(),
                            custPass.getText(),
                            0);
                    owner.addCustomer(newCust);
                    custList.add(newCust);
                    msg3.setText("");
                } else {
                    msg3.setText("Error: Username already exists");
                }
            }
            // Clear textfields
            custName.clear();
            custPass.clear();
        });

        // EventHandler: When delete customer button is clicked
        delCust.setOnAction((ActionEvent event) -> {

            try {
                // Select a row in table and delete from cusTb and custDB
                Customer cust = custTb.getSelectionModel().getSelectedItem();

                custTb.getItems().removeAll(cust);
                owner.delCustomer(cust);
                msg3.setText("");
            } catch (NullPointerException npe) {
                msg3.setText("Error: No customer is selected");
            }
        });

        // EventHandler: When back button is clicked
        backCust.setOnAction((ActionEvent event) -> {
            // Move to Scene 2: OwnerScreen
            primaryStage.setScene(ownerScreen);
            msg3.setText("");
        });

        // EventHandler: When buy book button is clicked
        buyBk.setOnAction((ActionEvent event) -> {
            // Check if the book in the ObservableList has been selected
            boolean select = false;
            int total = 0, pts = 0;
            ArrayList<Book> bkSel = new ArrayList<Book>();

            for (Book i : bkList) {
                if (i.getSelect().isSelected()) {
                    // Get total price of selected book(s) "i" and delete from bkList and bkDB.
                    total += i.getPrice();
                    pts += i.getPrice() * 10;

                    bkSel.add(i);
                    select = true;
                }
            }

            if (!select) {
                msg4.setText("Error: No book have been selected");
            } else {
                custDB.addPointsToCustomer(pts);
                Customer c = custDB.getCustomer();
                tc.setText("Total: " + total);
                pt.setText("Points: " + c.getPoints());
                st.setText("Status: " + c.getStatus());
                for (Book i : bkSel) {
                    bkTb.getItems().remove(i);
                    owner.delBook(i);
                }
                primaryStage.setScene(customerCostScreen);
                msg4.setText("");
            }
        });

        // EventHandler: When buy book with points button is clicked
        buyBkPts.setOnAction((ActionEvent event) -> {
            // Ceck if the book in the ObservableList has been selected.
            boolean select = false;
            int pts = 0, total = 0;
            ArrayList<Book> bkSel = new ArrayList<Book>();

            for (Book i : bkList) {
                if (i.getSelect().isSelected()) {
                    // Get total price of selected book(s) "i" and delete from bkList and bkDB.
                    total += i.getPrice();
                    pts += i.getPrice() * 10;

                    bkSel.add(i);
                    select = true;
                }
            }

            if (!select) {
                msg4.setText("Error: No book have been selected");
                return;
            }
            Customer c = custDB.getCustomer();
            int totalToPay = total - (c.getPoints() / 100);
            if (totalToPay < 0) {
                // points cover the cost
                tc.setText("Total: 0");
                custDB.deductPointsFromtCustomer(total * 100);
            } else {
                // points partially cover the cost
                tc.setText("Total: " + totalToPay);
                custDB.setPointsOfCustomer(0);
                custDB.addPointsToCustomer(totalToPay * 10);
            }
            c = custDB.getCustomer();
            pt.setText("Points: " + c.getPoints());
            st.setText("Status: " + c.getStatus());
            for (Book i : bkSel) {
                bkTb.getItems().remove(i);
                owner.delBook(i);
            }
            primaryStage.setScene(customerCostScreen);
            msg4.setText("");
        });

        // EventHandler: When logout button is clicked
        custLogout1.setOnAction((ActionEvent event) -> {
            // Move to Scene 1: Login Screen
            for (Book i : bkList) {
                if (i.getSelect().isSelected()) {
                    // Deselect all books
                    CheckBox unchk =  new CheckBox();
                    unchk.setSelected(false);
                    i.setSelect(unchk);
                }
            }

            primaryStage.setScene(loginScreen);
            msg4.setText("");
        });

        a6.getChildren().addAll(tc, pt, st, custLogout2);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
