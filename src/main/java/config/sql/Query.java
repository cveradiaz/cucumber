package config.sql;

import org.testng.Assert;

import java.sql.*;

public class Query {

    public static void creacionTabla() {
        {
            Connection connection = null;
            try {
                Class.forName( "org.sqlite.JDBC" );
                connection = DriverManager.getConnection( "jdbc:sqlite:sample.db" );
                Statement statement = connection.createStatement();
                statement.setQueryTimeout( 30 );  // set timeout to 30 sec.

                statement.executeUpdate( "drop table if exists productos" );
                statement.executeUpdate( "create table productos (id integer, nombreProducto string, marca string, modelo string, precioEfectivo integer, precioNormal integer)" );
            } catch (SQLException e) {
                System.err.println( e.getMessage() );
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    System.err.println( e );
                }
            }
        }
    }

    public static void insertarRegistros() {
        {

            Connection connection = null;
            try {
                Class.forName( "org.sqlite.JDBC" );
                connection = DriverManager.getConnection( "jdbc:sqlite:sample.db" );
                Statement statement = connection.createStatement();
                statement.setQueryTimeout( 30 );  // set timeout to 30 sec.
                statement.executeUpdate( "insert into productos values(1, 'placa madre', 'Asus', 'M/B Z390 TUF Plus Gaming WiFi (1151-v2)', 129990, 129990)" );
                statement.executeUpdate( "insert into productos values(1, 'placa madre', 'Asus', 'M/B + CPU Intel Celeron Prime J4005I-C', 69990, 69990)" );
                statement.executeUpdate( "insert into productos values(1, 'procesador', 'Intel', 'CPU Core i3-10100F 3.6GHz 6MB (1200)', 99990, 105290)" );
                statement.executeUpdate( "insert into productos values(1, 'procesador', 'Intel', 'CPU Core i5-9600K 3.70 GHz 9MB (1151-v2)', 269990, 284190)" );
                statement.executeUpdate( "insert into productos values(1, 'memoria ram', 'crucial', 'Memoria Notebook 16GB SoDimm DDR4 3200MHz Value RAM', 84990, 89490)" );
                statement.executeUpdate( "insert into productos values(1, 'memoria ram', 'crucial', 'DDR4 16GB 2400MHz Value RAM', 65990, 69490)" );
                statement.executeUpdate( "insert into productos values(1, 'tarjeta video', 'Asus', 'Video NVIDIA GTX1650 O4G TUF Gaming', 339990, 357890)" );
                statement.executeUpdate( "insert into productos values(1, 'torre', 'Gamdias', 'Gabinete ATX Athena M2 RGB', 79990, 84190)" );
                statement.executeUpdate( "insert into productos values(1, 'tarjeta sonido', 'Creative', 'Tarjeta Sonido Audigy RX PCIe 24 Bits', 59990, 63190)" );
                statement.executeUpdate( "insert into productos values(1, 'teclado mecanico', 'Gear Gamer', 'Teclado Gamer Volt Mecánico USB Inglés Negro', 37990, 39990)" );
                statement.executeUpdate( "insert into productos values(1, 'monitor gamer', 'MSI', 'Monitor Gamer 27\" G271 1ms 144 Hz FreeSync', 269990, 284190)" );
            } catch (SQLException e) {
                System.err.println( e.getMessage() );
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    System.err.println( e );
                }
            }
        }
    }

    public static void consultarRegistro(String nombre,String modelo, int precio) throws ClassNotFoundException {
        {

            Connection connection = null;
            try {
                Class.forName( "org.sqlite.JDBC" );
                connection = DriverManager.getConnection( "jdbc:sqlite:sample.db" );
                Statement statement = connection.createStatement();
                statement.setQueryTimeout( 30 );  // set timeout to 30 sec.
                ResultSet rs = statement.executeQuery(
                        "select * " +
                        "from productos " +
                                "where nombreProducto = '"+nombre+"'" +
                                "and modelo = '"+modelo+"'" +
                                "and precioProducto = "+precio+"" );
                if(rs.getRow() >= 0){
                    while (rs.next()) {
                        // read the result set
                        System.out.println( "id = " + rs.getString( "id" ) );
                        System.out.println( "nombre = " + rs.getString( "nombreProducto" ) );
                        System.out.println( "marca = " + rs.getInt( "marca" ) );
                        System.out.println( "modelo = " + rs.getString( "modelo" ) );
                        System.out.println( "precio = " + rs.getString( "precioProducto" ) );
                    }
                }else {
                    Assert.fail( "error en la validacion" );
                }

            } catch (SQLException e) {
                System.err.println( e.getMessage() );
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    System.err.println( e );
                }
            }
        }
    }

    public static int obtenerPrecioProductoBBDD(String nombreProducto,String modelo)  {
        {

            Connection connection = null;
            int precioEfectivo = 0;
            try {
                Class.forName( "org.sqlite.JDBC" );
                connection = DriverManager.getConnection( "jdbc:sqlite:sample.db" );
                Statement statement = connection.createStatement();
                statement.setQueryTimeout( 30 );  // set timeout to 30 sec.
                ResultSet rs = statement.executeQuery(
                        "select precioEfectivo " +
                                "from productos " +
                                "where nombreProducto = '"+nombreProducto+"'" +
                                "and modelo = '"+modelo+"'" );
                if(rs.getRow() >= 0){
                    while (rs.next()) {
                        precioEfectivo = Integer.parseInt( rs.getString( "precioEfectivo" ) );
                    }
                }else {
                    Assert.fail( "error al consultar precio, revisar." );
                }

            } catch (SQLException e) {
                System.err.println( e.getMessage() );
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    System.err.println( e );
                }
            }
            return precioEfectivo;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        creacionTabla();
        insertarRegistros();
        //consultarRegistro("Placa madre","M/B Z390 TUF Plus Gaming WiFi (1151-v2)",129990);
        System.out.println(obtenerPrecioProductoBBDD( "placa madre", "M/B Z390 TUF Plus Gaming WiFi (1151-v2)"));
    }
}
