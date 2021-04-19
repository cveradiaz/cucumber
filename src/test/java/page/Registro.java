package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static config.Constants.test_01;
import static config.Constants.urlDemoQaRegister;
import static drivers.DriverContext.getDriver;
import static utils.MetodosGenericos.esperar;
import static utils.MetodosGenericos.tomarFoto;
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
    @FindBy(xpath = "//*[@id=\"recaptcha-anchor\"]/div[1]")
    public WebElement checkCaptcha;
    @FindBy(xpath = "//*[@id='register']")
    public WebElement btnRegister;
    @FindBy(xpath = "//*[@title='reCAPTCHA']")
    public WebElement iFrameCaptcha;
    @FindBy(xpath = "//*[@title='recaptcha challenge']")
    public WebElement iFrameCaptchaVerify;
    @FindBy(xpath = "//*[@id='recaptcha-verify-button']")
    public WebElement btnVerify;


    public void navigateToRegister() {

        this.driver.navigate().to( urlDemoQaRegister );
        boolean elemento = visualizarObjeto(btnRegister, 5);
        if(elemento){
            System.out.println("Sacar foto de home");
        }
    }

    public void registrarUsuario(String firstName, String lastName, String userName, String password){
        boolean btn = visualizarObjeto(btnRegister,5);
        if(btn){
            esperar(5);
            inputFirstName.clear();
            inputFirstName.sendKeys(firstName);
            inputLastName.clear();
            inputLastName.sendKeys(lastName);
            inputUserName.clear();
            inputUserName.sendKeys(userName);
            inputPassword.clear();
            inputPassword.sendKeys(password);
            driver.switchTo().frame(iFrameCaptcha);
            System.out.println(checkCaptcha.isSelected());
            checkCaptcha.click();
            driver.switchTo().parentFrame();
            esperar(5);
            System.out.println("Campos llenos");
            System.out.println("sacar foto");

            tomarFoto(test_01, "evidencia");
            driver.switchTo().frame(iFrameCaptchaVerify);
            if(btnVerify.isDisplayed()){
                Assert.fail("Captcha activado");
            }else {
                btnRegister.click();
            }
            esperar(3);
        }
    }



}
