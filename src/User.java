import java.time.LocalDate;
import java.util.Scanner;

public class User {
    private int id;
    private String username;
    private String email;
    private LocalDate createdDate;

    // create a new user
    public User() {
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
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
