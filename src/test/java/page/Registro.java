package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static config.Constants.urlQA;
import static config.Constants.urlQARegister;
import static config.driver.DriverContext.getDriver;
import static config.reportepdf.ReportePdf.reporteObjetoDesplegado;
import static utils.MetodosGenericos.getResponseCode;
import static utils.Web.visualizarObjeto;

public class Registro {

    WebDriver driver;

    public Registro() {
        this.driver = getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id='firstname']")
    public WebElement inputFirstName;
    @FindBy(xpath = "//*[@id='lastname']")
    public WebElement inputLastName;
    @FindBy(xpath = "//*[@id='userName']")
    public WebElement inputUserName;
    @FindBy(xpath = "//*[@id='password']")
    public WebElement inputPassword;
    @FindBy(xpath = "//*[@class='recaptcha-checkbox-border']")
    public WebElement checkCaptcha;
    @FindBy(xpath = "//*[@id='register']")
    public WebElement btnRegister;

    public void navigateToRegister() {

        this.driver.navigate().to( urlQARegister );
        boolean elemento = visualizarObjeto(btnRegister, 5);
        if(elemento){
            System.out.println("Sacar foto de home");
        }


    }

    public void registrarUsuario(){

    }



}
