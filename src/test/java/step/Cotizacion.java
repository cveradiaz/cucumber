package step;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.DetalleProducto;
import page.Home;
import page.BuscarProducto;
import java.util.List;
import static config.driver.DriverContext.setUp;
import static config.excel.Metodos.getValor;
import static config.reportepdf.ReportePdf.nombreClase;
import static config.sql.Query.obtenerPrecioProductoBBDD;

public class Cotizacion {
    private static int precioProducto;
    WebDriver driver = null;
    Home homePage;
    BuscarProducto productoPage;
    DetalleProducto detalleProductoPage;

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Given("el usuario ingresa a la pagina de PCFactory")
    public void elUsuarioIngresaALaPaginaDePCFactory() {
        nombreClase(this.getClass().getSimpleName());
        setUp( "chrome" );
        homePage = new Home();
        homePage.navigateToHomePage();
    }

    @And("busca y selecciona productos")
    public void buscarYSeleccionarProductos(DataTable dataTable) {
        productoPage = new BuscarProducto();
        detalleProductoPage = new DetalleProducto();

        List<List<String>> dato = dataTable.raw();
        for (int i = 1; i < dato.size(); i++) {
            homePage.buscarProducto(dato.get( i ).get( 0 ));
            productoPage.seleccionarProducto(dato.get( i ).get( 1 ));
            detalleProductoPage.agregarAlCarro();
            String producto = getValor((i-1));
            String modelo = getValor(i);
            System.out.println("producto >"+producto);
            System.out.println("modelo >"+modelo);
            int precio = obtenerPrecioProductoBBDD( producto, modelo);
            System.out.println("precio bbdd > "+precio);
            Assert.assertEquals( detalleProductoPage.precioProducto(), precio );
        }

    }

    @Then("^la cotizacion debe mostrar 8 productos  en el carro de compras$")
    public void validarCantidadDeProductosEnCotizacion() throws Throwable {
        Assert.assertEquals( detalleProductoPage.cantidadProductosCarrito(), 8);

    }

    @And("^mostrar el detalle y valor total a pagar$")
    public void validarSumaDeProductosEnCotizacion() throws Throwable {
        Assert.assertEquals( detalleProductoPage.sumaProductosCarrito(),1317920 );
        precioProducto = obtenerPrecioProductoBBDD("Asus", "M/B Z390 TUF Plus Gaming WiFi (1151-v2)");

    }

    /*
         validar en bbdd el precio mostrado por el front
         validar con excell
     */
}
