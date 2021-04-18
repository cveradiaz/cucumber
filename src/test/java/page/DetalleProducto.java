package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static config.driver.DriverContext.getDriver;
import static utils.Web.visualizarObjeto;

public class DetalleProducto {

    WebDriver driver;

    public DetalleProducto() {
        this.driver = getDriver();
        PageFactory.initElements(this.driver, this);
    }

    /*@FindBy(xpath = "//*[contains(text(),'Agregar al Carro')]")
    public WebElement btnAgregarCarro;*/
    @FindBy(xpath = "//*[@class='nombre' and text() = '*'")
    public WebElement producto;
    @FindBy(xpath = "//*[@id='agrega_carro']")
    public WebElement btnAgregarCarro;

    public void agregarAlCarro(){
        boolean agregar = visualizarObjeto(btnAgregarCarro, 5);
        if(agregar){
            btnAgregarCarro.click();
        }
    }

    public int cantidadProductosCarrito(){
        int registros=0;
        List<WebElement> productos = driver.findElements( By.xpath( "//*[@class='caluga-vertical']" ) );
        for(WebElement producto : productos){
            registros++;
        }
        return registros;
    }

    public int precioProducto(){
        int precio=0; String valor="";
        List<WebElement> productos = driver.findElements( By.xpath( "//*[@id='producto_subtotal']" ) );
        for(WebElement producto : productos){
            valor = producto.getText().replace( "$","" ).replace( ".","" ).trim();
            precio = Integer.parseInt( ( valor) );
            break;
        }
        return precio;
    }

    public int sumaProductosCarrito(){
        int suma=0; String valor="";
        List<WebElement> productos = driver.findElements( By.xpath( "//*[@id='producto_subtotal']" ) );
        for(WebElement producto : productos){
            valor = producto.getText().replace( "$","" ).replace( ".","" ).trim();
            suma = suma + Integer.parseInt( ( valor) );
        }
        return suma;
    }
}
