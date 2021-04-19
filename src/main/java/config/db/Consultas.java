package config.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Consultas extends Conexion {

    private Connection connection = getConnection();

    public List<String> consultaProductos(String idProducto) {

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM productos WHERE idProductos = '" + idProducto + "'");

            List<String> datos = new ArrayList<>();
            while (result.next()) {
                datos.add(result.getString("idProductos"));
                datos.add(result.getString("nombreProductos"));
                datos.add(result.getString("marcaProductos"));
                datos.add(result.getString("precioNormalProducto"));
                datos.add(result.getString("precioEfectivoProducto"));
                datos.add(result.getString("urlProducto"));
            }
            return datos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public List<String> consultaID() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT idProductos FROM productos;");
            List<String> id = new ArrayList<>();
            while (result.next()) {
                id.add(result.getString("idProductos"));
            }
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
