package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static config.Constants.urlDemoQaFormulario;
import static config.Constants.urlDemoQaRegister;
import static drivers.DriverContext.getDriver;
import static utils.Web.visualizarObjeto;

public class Formulario {

    WebDriver driver;

    public Formulario() {
        this.driver = getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id='firstName']")
    public WebElement inputFirstName;
    @FindBy(xpath = "//*[@id='lastName']")
    public WebElement inputLastName;
    @FindBy(xpath = "//*[@id='userEmail']")
    public WebElement inputEmail;
    @FindBy(xpath = "//*[@id='gender-radio-1']")
    public WebElement radioMale;
    @FindBy(xpath = "//*[@id='gender-radio-2']")
    public WebElement radioFemale;
    @FindBy(xpath = "//*[@id='gender-radio-3']")
    public WebElement radioOther;
    @FindBy(xpath = "//*[@id='userNumber']")
    public WebElement inputMobile;
    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    public WebElement inputDayOfBirthDate;
    @FindBy(xpath = "//*[@id='hobbies-checkbox-1']")
    public WebElement checkSports;
    @FindBy(xpath = "//*[@id='hobbies-checkbox-2']")
    public WebElement checkReading;
    @FindBy(xpath = "//*[@id='hobbies-checkbox-3']")
    public WebElement checkMusic;
    @FindBy(xpath = "//*[@id='currentAddress']")
    public WebElement inputAddress;
    @FindBy(xpath = "//*[@id='submit']")
    public WebElement btnSubmit;
    @FindBy(xpath = "//*[@class='modal-content']")
    public WebElement modal;
    @FindBy(xpath = "//*[@id='closeLargeModal']")
    public WebElement btnClose;


    public void navigateToFormulario() {

        this.driver.navigate().to( urlDemoQaFormulario );
        boolean elemento = visualizarObjeto(btnSubmit, 5);
        if(elemento){
            System.out.println("Sacar foto de Formulario");
        }
    }
}
