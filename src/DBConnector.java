import java.sql.*;
public class DBConnector {
    private final String dbName = "librarydatabase";
    private final String dbUsername = "root";
    private final String dbPassword = "password";
    private static Connection con;
    //private final Connection sqlConnect;
    private Statement sqlStatement;

    public DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        /*this.dbName = givenDbName;
        this.dbUsername = givenDbUsername;
        this.dbPassword = givenDbPassword;
        */
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydatabase",dbUsername,dbPassword);

        this.sqlStatement = con.createStatement();
    }


    public Connection getConn(Statement statement) throws SQLException {
        return con;
    }


    public String getDbName(){
        return dbName;
    }
    public String getDbUsername(){
        return dbUsername;
    }
    public String getDbPassword(){
        return dbPassword;
    }
}
