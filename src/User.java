import java.util.Scanner;

public class User {
    private String username;

    // create a new user
    public User() {
        System.out.println("Please choose a username!");
        Scanner in = new Scanner(System.in);
        this.username = in.nextLine();
        System.out.println("Username: " + username);
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    // remove user

}
