package config.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    static Connection connection;
    static String url = "jdbc:mysql://127.0.0.1:3306/demoqa";
    static String user = "root";
    static String pass = "1234";

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
