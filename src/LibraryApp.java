import java.sql.*;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
    public static boolean isRunning;
    private static final Scanner in = new Scanner(System.in);
    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<String> collections = new ArrayList<>();
    private static User selectedUser = null;
    private static boolean userSet;
    private Statement sqlStatement;

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydatabase", "root","password");

    public LibraryApp() throws SQLException, ClassNotFoundException {
        isRunning = true;
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.sqlStatement = con.createStatement();
    }
    public void programLoop() throws SQLException {
        while (isRunning) {
            mainMenu();
            // pick user
            int input = readInt("->", 4);
            if (input == 1) {
                createUser();
            }
            if (input == 2) {
                ///loginUser(users);
            }
            if (input == 3) {
                viewUsers();
            }
            if (input == 4) {
                System.exit(1);
            }
        }
    }
    public void createUser() throws SQLException{
        Scanner in = new Scanner(System.in);
        System.out.println("Please input a username");
        String userAccountName = in.nextLine();

        System.out.println("Please input a Email");
        String userEmail = in.nextLine();

        LocalDate userDateCreated = LocalDate.now();
        PreparedStatement prepStatement = con.prepareStatement("insert into userAccounts (username,email,createdDate)" + "values (?,?,?)");
        prepStatement.setString(1,userAccountName);
        prepStatement.setString(2,userEmail);
        prepStatement.setDate(3, Date.valueOf(userDateCreated));

        int newUser = prepStatement.executeUpdate();


    }

    public void viewUsers() throws SQLException {
        ResultSet result = makeQuery("SELECT * FROM userAccounts");
        while(result.next()) {
            System.out.println(result.getString("username"));
        }
        programLoop();
        }

    public void mainMenu(){
        clearConsole();
        System.out.println("What would you like to do?: ");
        System.out.println("1. Create a new user");
        System.out.println("2. Log in to current user");
        System.out.println("3. View all users");
        System.out.println("4.Exit program!");
    }

    public void userMenu() throws SQLException {
        System.out.println("What would you like to do?: ");
        System.out.println("1. Create a new collection");
        System.out.println("2. View collections");
        System.out.println("3. Settings");
        System.out.println("4. logout");
        int input = readInt("->", 4);
        if (input == 1){
            Collection col = new Collection();
            col.createCollection();
            userMenu();
            col.showCollectionInfo();
        }if (input == 2){
            userMenu();
        }if (input == 3){
            settingsMenu();
        }
        if (input == 4){
            LogoutUser();
            userMenu();
        }
    }
    public  void settingsMenu() throws SQLException {
        System.out.println("Settings:");
        System.out.println("1. Change username");
        System.out.println("2. Go back");
        int input = readInt("->", 4);
        if (input == 1){
            changeUsername();
        }if (input == 2){
            userMenu();
        }
    }

    public static int readInt(String prompt, int userChoices) {
        int input;
        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(in.next());
            } catch (Exception e) {
                input = -1;
                System.out.println("Please input an integer!");
            }
        } while (input < 1 || input > userChoices);
        return input;
    }

    public void clearConsole() {
        for (int i = 0; i < 1; i++) {
            System.out.println();
        }
    }

    /*public void loginUser(ArrayList<User> users) throws SQLException {
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Please input a user");
            String userSearch = in.next();

            ResultSet result = db.makeQuery("select * from useraccounts where username="+userSearch;);
            while(result.next()) {
                System.out.println(result.getString("username") + " " + result.getString("email"));
                selectedUser = result.getString("username");
            }

            for (User user : users) {
                if (user.getUsername().contains(userSearch)) {
                    selectedUser = user;
                    userSet = true;
                }
            }
        }while (!userSet) ;
        System.out.println("Welcome " + selectedUser.getUsername());
        userMenu();

    }*/
    public void LogoutUser() throws SQLException {
        userSet = false;
        selectedUser = null;
        System.out.println("You are now logged out");
        programLoop();
    }

    public void changeUsername() throws SQLException {
        System.out.println("Choose a new username: ");
        String newUsername = in.next();
        selectedUser.setUsername(newUsername);
        System.out.println("Username changed!  " + selectedUser.getUsername());
        userMenu();
    }
    public void createCollection(){

    }

    public ResultSet makeQuery(String givenStatement) throws SQLException {
        setSqlStatement(givenStatement);
        return sqlStatement.executeQuery(givenStatement);
    }
    private void setSqlStatement(String givenStatement) {
        this.sqlStatement = sqlStatement;
    }
}


