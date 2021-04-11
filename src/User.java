import java.time.LocalDate;
import java.util.Scanner;

public class User {
    private int id;
    private String username;
    private String email;
    private LocalDate creationDate;
    public Collection collection;

    // create a new user
    public User() {
        System.out.println("Please choose a username!");
        Scanner in = new Scanner(System.in);
        this.username = in.nextLine();
        System.out.println("Username: " + username);
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
    // remove user

}
