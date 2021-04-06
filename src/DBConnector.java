import java.sql.*;
public class DBConnector {
    private final String dbName;
    private final String dbUsername;
    private final String dbPassword;
    private final Connection sqlConnect;
    private Statement sqlStatement;

    public DBConnector(String givenDbName, String givenDbUsername, String givenDbPassword) throws ClassNotFoundException, SQLException {
        Class.forName("com.sql.jdbc.Driver");
        this.dbName = givenDbName;
        this.dbUsername = givenDbUsername;
        this.dbPassword = givenDbPassword;
        this.sqlConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+givenDbName,givenDbName, givenDbPassword);
        this.sqlStatement = this.sqlConnect.createStatement();
    }

    public ResultSet makeQuery(String givenStatement) throws SQLException {
        setSqlStatement(givenStatement);
        return sqlStatement.executeQuery(givenStatement);
    }

    private void setSqlStatement(String givenStatement) {
        this.sqlStatement = sqlStatement;
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
