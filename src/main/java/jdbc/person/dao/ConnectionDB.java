package jdbc.person.dao;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//singleton patter
public class ConnectionDB {

    private Connection connection;
    private static ConnectionDB instance;

    private ConnectionDB() {

    }

    public static ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection != null) {
            DriverManager.registerDriver(new Driver());
           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "root");
        }
        return connection;
    }
}
