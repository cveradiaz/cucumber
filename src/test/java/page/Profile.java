package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static config.Constants.test_03;
import static drivers.DriverContext.getDriver;
import static utils.MetodosGenericos.tomarFoto;
import static utils.Web.esperarElemento;

public class Profile {

    WebDriver driver;

    public Profile() {
        this.driver = getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[contains(text(),'Delete Account')]")
    public WebElement btnDeleteAccount;

    public void obtenerEvidencia(){
        boolean btn = esperarElemento(btnDeleteAccount);
        if(btn){
            System.out.println("[ Registrando evidencia ]");
            tomarFoto(test_03, "evidencia-bookstore");
        }else {
            Assert.fail("Algo paso al esperar elemento");
        }
    }
}
