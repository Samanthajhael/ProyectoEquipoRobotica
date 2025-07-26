import java.sql.*;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://ukezh1ojnunqubzv:QUAHmatVNNTzTbPltHFd@bcvjojpqho2j01wr9g3b-mysql.services.clever-cloud.com:3306/bcvjojpqho2j01wr9g3b";
    private static final String USER = "ukezh1ojnunqubzv";
    private static final String PASSWORD = "QUAHmatVNNTzTbPltHFd";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
} 