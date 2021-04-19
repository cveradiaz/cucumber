package config.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection connection;
    String url = "jdbc:mysql://127.0.0.1:3306/productos_pcfactory";
    String user = "root";
    String pass = "admin";

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
