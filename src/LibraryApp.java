import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static boolean isRunning;
    public static boolean loggedIn;
    private static final Scanner in = new Scanner(System.in);
    //private final List<Collection> userCollections = new ArrayList<Collection>();
    private Statement sqlStatement;

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydatabase", "root","password");

    public LibraryApp() throws SQLException, ClassNotFoundException {
        isRunning = true;
        loggedIn = false;
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.sqlStatement = con.createStatement();
    }
    public void programLoop(User u) throws SQLException, IOException, ClassNotFoundException {
        while (isRunning && !loggedIn) {
            mainMenu(u);
            }
        while(isRunning && loggedIn) {
            userMenu(u);
        }
    }

    private User loginUser() throws SQLException {
        User u = null;
        System.out.println("Input Username");
        String userAccountName = in.next();
        try {
            PreparedStatement prepStatement = con.prepareStatement("SELECT * FROM useraccounts WHERE username = (?)");
            prepStatement.setString(1, userAccountName);
            ResultSet r1 = prepStatement.executeQuery();
            if (r1.next()) {
                if(r1.getInt("isAdmin") == 0) {
                    u = new User();
                    u.setID(r1.getInt("userID"));
                    u.setUsername(r1.getString("username"));
                    u.setEmail(r1.getString("email"));
                    u.setCreatedDate(r1.getDate("createdDate").toLocalDate());
                    userMenu(u);
                }else {
                    u = new Librarian();
                    u.setID(r1.getInt("userID"));
                    u.setUsername(r1.getString("username"));
                    u.setEmail(r1.getString("email"));
                    u.setCreatedDate(r1.getDate("createdDate").toLocalDate());
                    adminMenu(u);
                }
                System.out.println("Welcome: " + u.getUsername());
                loggedIn = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return u;

    }

    public User createUser() throws SQLException{
        User u = new User();
        PreparedStatement prepStatement = con.prepareStatement("insert into userAccounts (username,email,createdDate)" + "values (?,?,?)");
        prepStatement.setString(1,u.getUsername());
        prepStatement.setString(2,u.getEmail());
        prepStatement.setDate(3, Date.valueOf(u.getCreatedDate()));
        prepStatement.executeUpdate();
        return u;
    }

    public void viewUsers() throws SQLException {
        ResultSet result = makeQuery("SELECT * FROM userAccounts");
        while(result.next()) {
            System.out.println(result.getString("username"));
        }
        }

    public void mainMenu(User u) throws SQLException {
        clearConsole();
        System.out.println("What would you like to do?: ");
        System.out.println("1. Create a new user");
        System.out.println("2. Log in to current user");
        System.out.println("3. View all users");
        System.out.println("4.Exit program!");
        int input = readInt("->", 4);
        if (input == 1) {
            createUser();
        }
        if (input == 2) {
            loginUser();
        }
        if (input == 3) {
            viewUsers();
        }
        if (input == 4) {
            System.exit(1);
            }
        }


    public void userMenu(User u) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Welcome user " + u.getUsername());
        System.out.println("What would you like to do?: ");
        System.out.println("1. Add a new book");
        System.out.println("3. Settings");
        System.out.println("4. logout");
        int input = readInt("->", 4);
        if (input == 1) {
            newCollection(u);
        }
        if (input == 2) {
            //retrieveCollections(u);
            loadCollection(u);
        }
        if (input == 3) {
            settingsMenu(u);
        }
        if (input == 4) {
            LogoutUser();
            programLoop(u);
        }
    }
        public void adminMenu(User u) throws SQLException, IOException, ClassNotFoundException {
            System.out.println("Welcome Admin " + u.getUsername());
            System.out.println("What would you like to do?: ");
            System.out.println("1. Add a new book");
            System.out.println("3. Settings");
            System.out.println("4. logout");
            int input = readInt("->", 4);
            if (input == 1) {
                newBook(u);
            }
            if (input == 2) {
                //retrieveCollections(u);
                loadCollection(u);
            }
            if (input == 3) {
                settingsMenu(u);
            }
            if (input == 4) {
                LogoutUser();
                programLoop(u);
            }

    }

    private Book newBook(User u) throws SQLException, IOException, ClassNotFoundException {
        Book b = new Book();
        b.createBook();
        PreparedStatement prepStatement = con.prepareStatement("insert into books (bookTitle, bookSeries, bookAuthor, bookPageCount, bookPublicationDate, bookISBN)" + "values (?,?,?,?,?,?)");
        prepStatement.setString(1,b.getBookTitle());
        prepStatement.setString(2, b.getBookSeries());
        prepStatement.setString(3, b.getBookAuthor());
        prepStatement.setInt(4, b.getBookPageCount());
        prepStatement.setDate(5, Date.valueOf(b.getBookPublicationDate()));
        prepStatement.setLong(6, b.getBookISBN());
        prepStatement.executeUpdate();
        b.showBookInfo();
        adminMenu(u);
        return b;
    }


    public Collection newCollection(User u) throws SQLException, IOException, ClassNotFoundException {
        Collection col = new Collection();
        col.createCollection();
        col.showCollectionInfo();
        //userCollections.add(col);
        PreparedStatement prepStatement = con.prepareStatement("insert into collections (colName,colDesc,colCreatedDate,userID)" + "values (?,?,?,?)");
        prepStatement.setString(1,col.getName());
        prepStatement.setString(2, col.getDesc());
        prepStatement.setDate(3, Date.valueOf(col.getCreatedDate()));
        prepStatement.setInt(4, u.getID());
        prepStatement.executeUpdate();
        return col;
    }
    public Collection loadCollection(User u) throws SQLException, IOException, ClassNotFoundException {
        Collection col = null;
        System.out.println("Which collection would you like ot view");
        String colPick = in.next();
        try {
            PreparedStatement prepStatement = con.prepareStatement("SELECT * FROM collections WHERE (userID, colName) = (?,?)");
            prepStatement.setInt(1, u.getID());
            prepStatement.setString(2, colPick);
            ResultSet r3 = prepStatement.executeQuery();
            if(r3.next()) {
                col = new Collection();
                col.setCollectionID(r3.getInt("colID"));
                col.setName(r3.getString("colName"));
                col.setDesc(r3.getString("colDesc"));
                col.setCreatedDate(r3.getDate("colCreatedDate").toLocalDate());
                col.setUserID(r3.getInt("userID"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            addBookToCollection(u,col);
        }
        return col;
    }

    private void addBookToCollection(User u, Collection col) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Enter book title or press 1 to exit");
        Book b = null;
        while (true) {
            Scanner in = new Scanner(System.in);
            String bookTitle = in.nextLine();
            try {
                PreparedStatement prepStatement = con.prepareStatement("SELECT * FROM books WHERE bookTitle = (?)");
                prepStatement.setString(1, bookTitle);
                ResultSet r1 = prepStatement.executeQuery();
                if(r1.next()) {
                    b = new Book();
                    b.setBookID(r1.getInt("bookID"));
                    b.setBookTitle(r1.getString("bookTitle"));
                    b.setBookAuthor(r1.getString("bookAuthor"));
                    saveBookToCollection(u, col, b);
                    col.addToBookArray(b);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("book added: " + b.getBookTitle());
            System.out.println("Add new book : ");
            System.out.println("");
            if(bookTitle.equals("1")){
                break;
            }
        }
        System.out.println("Books added to collection");
        userMenu(u);
    }
    public void saveBookToCollection(User u, Collection col, Book b) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("insert into interactions (userID,colID,bookID)" + "values (?,?,?)");
        prepStatement.setInt(1,u.getID());
        prepStatement.setInt(2, col.getCollectionID());
        prepStatement.setInt(3, b.getBookID());
        prepStatement.executeUpdate();
    }
    public void settingsMenu(User u) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Settings:");
        System.out.println("2. Change Username");
        System.out.println("2. Change Email");
        System.out.println("3. Go back");
        int input = readInt("->", 4);
        if (input == 1){
            changeUsername(u);
        }if (input == 2){
            changeEmail(u);
        }if(input == 3){
            userMenu(u);
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

    public void LogoutUser(){
        loggedIn = false;
        System.out.println("You are now logged out");
    }

    public void changeUsername(User u) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Choose a new username: ");
        String newUsername = in.next();
        int updateID = u.getID();
        PreparedStatement prepStatement = con.prepareStatement("update userAccounts set username=? where userID=?");
        prepStatement.setString(1,newUsername);
        prepStatement.setInt(2, updateID);
        prepStatement.executeUpdate();
        System.out.println("Username changed!");
        userMenu(u);
    }
    private void changeEmail(User u) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Choose a new email: ");
        String newEmail = in.next();
        int updateID = u.getID();
        PreparedStatement prepStatement = con.prepareStatement("update userAccounts set email=? where userID=?");
        prepStatement.setString(1,newEmail);
        prepStatement.setInt(2, updateID);
        prepStatement.executeUpdate();
        System.out.println("Email changed!");
        userMenu(u);
    }
    public Collection retrieveCollections(User u) throws SQLException, IOException, ClassNotFoundException {
        int userID = u.getID();
        Collection col = null;
        PreparedStatement prepStatement = con.prepareStatement("SELECT * FROM collections where userID=?");
        prepStatement.setInt(1, userID);
        ResultSet r2 = prepStatement.executeQuery();
        while(r2.next()){
            byte[] collIn = (byte[]) r2.getObject(1);
            ByteArrayInputStream byteInColl = new ByteArrayInputStream(collIn);
            ObjectInputStream objInColl = new ObjectInputStream(byteInColl);
            col = (Collection) objInColl.readObject();
            System.out.println("Collection: " + col);
        }
        return col;
    }
    public ResultSet makeQuery(String givenStatement) throws SQLException {
        setSqlStatement(givenStatement);
        return sqlStatement.executeQuery(givenStatement);
    }
    private void setSqlStatement(String givenStatement) {
        this.sqlStatement = sqlStatement;
    }
}


