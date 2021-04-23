package config.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Consultas extends Conexion {

    public static void main(String[] args) {
        //consultaID();
        //insertarRegistroLogin("clau","clau2222");
        consultarRegistroPersona();
    }

    private static Connection connection = getConnection();

    public static List<String> consultaProductos(String idProducto) {

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

    public static List<String> consultaID() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT isbn FROM libro;");
            List<String> id = new ArrayList<>();
            while (result.next()) {
                id.add(result.getString("isbn"));
            }
            System.out.println(id);
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static boolean insertarRegistroLogin(String user, String pass){
        boolean usuario = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("INSERT INTO login values ('"+user+"','"+pass+"') ");
            while (result.next()) {
            }
            System.out.println("[ Usuario registrado en la BBDD ]");
            usuario = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuario;
    }

    public static List<String> consultarRegistroLogin(){
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM login");
            List<String> datos = new ArrayList<>();
            while (result.next()) {
                System.out.println("Usuario creado");
                datos.add(result.getString("usuario"));
                datos.add(result.getString("password"));
            }
            return datos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static List<String> consultarRegistroPersona(){
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM persona");
            List<String> datos = new ArrayList<>();
            while (result.next()) {
                datos.add(result.getString("name"));
                datos.add(result.getString("last_name"));
                datos.add(result.getString("email"));
                datos.add(result.getString("gender"));
                datos.add(result.getString("mobile"));
                datos.add(result.getString("date_birth"));
                datos.add(result.getString("current_address"));
                datos.add(result.getString("state"));
                datos.add(result.getString("city"));
            }
            System.out.println(datos);
            return datos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static int consultarRegistroLibro(String isbn, String titulo, String subTitulo, String autor,
                                                      String publisher, String totalPages, String webSite){
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("" +
                    "select count(1) from libro \n" +
                    "where isbn = '"+isbn+"' \n" +
                    "and tile_book = '"+titulo+"'\n" +
                    "and Subtitle_book = '"+subTitulo+"'\n" +
                    "and author_book = '"+autor+"'\n" +
                    "and publisher = '"+publisher+"'\n" +
                    "and total_page = '"+totalPages+"'\n" +
                    "and web_site = '"+webSite+"'");

            while (result.next()) {
                count = result.getRow();
            }
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}
