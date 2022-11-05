package bookstore;

import javafx.scene.control.CheckBox;

public class Book {
    private String title;
    private int price;
    private CheckBox select;
    
    public Book(String name, int price) {
        this.title = name;
        this.price = price;
        this.select = new CheckBox();
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public CheckBox getSelect(){
        return select;
    }
    
    public void setSelect(CheckBox select){
        this.select = select;
    }
    
    @Override
    public String toString() {
        return this.getPrice() + " " + this.getTitle();
    }
}
