package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static config.Constants.urlQA;
import static config.driver.DriverContext.getDriver;
import static config.reportepdf.ReportePdf.reporteObjetoDesplegado;
import static utils.MetodosGenericos.esperar;
import static utils.MetodosGenericos.getResponseCode;
import static utils.Web.visualizarObjeto;

public class HomeDemo {

    WebDriver driver;

    public HomeDemo() {
        this.driver = getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@class='card mt-4 top-card'][1]")
    public WebElement btnElements;

    public void navigateToHomePage() {

        driver.navigate().to( urlQA );
        boolean elemento = visualizarObjeto(btnElements, 5);
        if(elemento){
            System.out.println("Sacar foto de home");
        }

    }
}
