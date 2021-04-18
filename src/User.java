import java.time.LocalDate;
import java.util.Scanner;

public class User {
    private int ID;
    private String username;
    private String email;
    private LocalDate createdDate;

    // create a new user
    public User() {
    }
    public void createUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please input a username");
        String username = in.nextLine();
        setUsername(username);
        System.out.println("Please input a Email");
        String email = in.nextLine();
        setEmail(email);
        createdDate = LocalDate.now();
        setCreatedDate(createdDate);

    }
    public void setID(int ID){
        this.ID = ID;
    }
    public int getID(){
        return ID;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setCreatedDate(LocalDate createdDate){
        this.createdDate = createdDate;
    }
    public LocalDate getCreatedDate(){
        return createdDate;
    }
    // remove user

}
