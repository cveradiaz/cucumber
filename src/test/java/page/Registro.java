package page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static config.Constants.test_01;
import static config.Constants.urlDemoQaRegister;
import static config.db.Consultas.insertarRegistroLogin;
import static drivers.DriverContext.getDriver;
import static utils.MetodosGenericos.esperar;
import static utils.MetodosGenericos.tomarFoto;
import static utils.Web.*;

public class Registro {

    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(getDriver(), 120);

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
    @FindBy(xpath = "//*[@id='name']")
    public WebElement pUserExist;
    @FindBy(xpath = "//*[@id='recaptcha-anchor']/div[4]")
    public WebElement captchaPass;


    public void navigateToRegister() {

        this.driver.navigate().to( urlDemoQaRegister );
        boolean elemento = visualizarObjeto(btnRegister, 5);
        if(elemento){
            System.out.println("[ Home ]");
        }
    }

    public void registrarUsuario(String firstName, String lastName, String userName, String password){
        boolean btn = visualizarObjeto(btnRegister,5);
        if(btn){
            inputFirstName.sendKeys(firstName);
            inputLastName.sendKeys(lastName);
            inputUserName.sendKeys(userName);
            inputPassword.sendKeys(password);
            System.out.println("[ Seleccionar el captcha manual ]");
            driver.switchTo().frame(iFrameCaptcha);
            checkCaptcha.click();
            /*
               Proceso manual
             */
            if (wait.until(ExpectedConditions.attributeContains(iFrameCaptcha, "aria-checked", "true"))) {
                driver.switchTo().defaultContent();
                boolean registro = visualizarObjeto(btnRegister,5);
                if(registro){
                    btnRegister.click();
                    boolean existe = visualizarObjeto(pUserExist,5);
                    if(existe){
                        Assert.fail("El usuario existe, intenta cambiar el dato de prueba");
                    }else{
                        insertarRegistroLogin(firstName, password);
                    }
                }else {
                    Assert.fail("error con el boton Registrar");
                }
            }else{
                Assert.fail("error de timeout al seleccionar captcha");
            }
        }
    }

    public void validarAltaUsuario(){
        Alert alert = driver.switchTo().alert();
        String creado = alert.getText();
        System.out.println(creado);
        if(creado.contains("User Register Successfully")){
            System.out.println("[ usuario creado");
            tomarFoto(test_01, "evidencia");
            acceptAlert();
        }else{
            Assert.fail("Algo paso al crear el usuario");
        }
    }

}
