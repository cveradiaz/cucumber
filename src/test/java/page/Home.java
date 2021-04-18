package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static config.Constants.urlQA;
import static config.driver.DriverContext.getDriver;
import static config.reportepdf.ReportePdf.reporteObjetoDesplegado;
import static utils.MetodosGenericos.getResponseCode;
import static utils.Web.visualizarObjeto;

public class Home {

    WebDriver driver;

    public Home() {
        this.driver = getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id='search']")
    public WebElement inputTextBusqueda;
    @FindBy(xpath = "//*[@id='search_btn']")
    public WebElement btnBusqueda;

    public void buscarProducto(String producto){
        boolean busqueda = visualizarObjeto(inputTextBusqueda,5);
        if(busqueda){
            inputTextBusqueda.clear();
            inputTextBusqueda.sendKeys(producto);
            reporteObjetoDesplegado(true, "ingresar busqueda", "Home", false);
        }else {
            reporteObjetoDesplegado(false, "error al ingresar busqueda", "Home", false);
        }

        boolean buscar = visualizarObjeto(btnBusqueda,5);
        if(buscar){
            btnBusqueda.click();
            reporteObjetoDesplegado(true, "seleccionar boton busqueda", "Home", false);
        }else {
            reporteObjetoDesplegado(false, "error al seleccionar boton busqueda", "Home", false);
        }

    }

    public void navigateToHomePage() {
        int cod = getResponseCode(urlQA);
        if(cod == 200){
            this.driver.navigate().to( urlQA );
            reporteObjetoDesplegado(true, "Se valida el despliegue del sitio", "Home", false);
        }else{
            reporteObjetoDesplegado(false, "error al validar el despliegue del sitio", "Home", false);
        }

    }
}
