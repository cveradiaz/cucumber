package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static config.driver.DriverContext.getDriver;
import static config.reportepdf.ReportePdf.reporteObjetoDesplegado;

public class BuscarProducto {

    WebDriver driver;

    public BuscarProducto() {
        this.driver = getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id='ordenar_por']")
    public WebElement cboOrdenarPor;
    @FindBy(xpath = "//*[@class='nombre' and text() = '*'")
    public WebElement producto;
    @FindBy(xpath = "//*[@id='seccion-banner-bajo']/a/img")
    public WebElement imgBanner2;

    public void seleccionarProducto(String nombreProducto){
        List<WebElement> productos = driver.findElements( By.xpath( "//*[@class='nombre' and text() = '"+nombreProducto+"']" ) );
        for(WebElement producto : productos){
            if(producto.isEnabled()){
                producto.click();
                reporteObjetoDesplegado(true, "buscar producto", "Buscar", false);
                break;
            }else{
                reporteObjetoDesplegado(false, "error al buscar producto, no encontrado:[ "+nombreProducto+" ]", "Buscar", false);
            }

        }
    }
}
