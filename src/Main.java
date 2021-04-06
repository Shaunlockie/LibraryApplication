import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        LibraryApp libApp = new LibraryApp();
        System.out.println("Running Library App");
        libApp.programLoop();
}
}
