package bookstore;

public class Customer {
    private int points;
    private String username;
    private String password;
    private Status status;
    
    public Customer(String username, String password, int points) {
        this.username = username;
        this.password = password;
        this.points = points;
        
        if(this.points < 1000) status = new Silver();
        else status = new Gold();
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    public void deductPoints(int pointsToDeduct) {
        points -= pointsToDeduct;
    }
    
    public void setStatus() {
        if(getPoints() < 1000) status = new Silver();
        else status = new Gold();
    }
    
    public String getStatus() {
        return status.getStatus();
    }
    
    @Override
    public String toString() {
        return this.getUsername() + " " + this.getPassword() + " " 
                + this.getPoints();
    }
}