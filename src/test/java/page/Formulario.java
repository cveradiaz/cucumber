package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static config.Constants.test_02;
import static config.Constants.urlDemoQaFormulario;
import static drivers.DriverContext.getDriver;
import static utils.MetodosGenericos.*;
import static utils.Web.esperarElemento;
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
    @FindBy(xpath = "//*[@id='genterWrapper']/div[2]/div[1]/label")
    public WebElement radioMale;
    @FindBy(xpath = "//*[@id='genterWrapper']/div[2]/div[2]/label")
    public WebElement radioFemale;
    @FindBy(xpath = "//*[@id='genterWrapper']/div[2]/div[3]/label")
    public WebElement radioOther;
    @FindBy(xpath = "//*[@id='userNumber']")
    public WebElement inputMobile;
    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    public WebElement inputDayOfBirthDate;
    @FindBy(xpath = "//*[@id='subjectsInput']")
    public WebElement inputSubject;
    @FindBy(xpath = "//*[@id='hobbiesWrapper']/div[2]/div[1]/label")
    public WebElement checkSports;
    @FindBy(xpath = "//*[@id='hobbiesWrapper']/div[2]/div[2]/label")
    public WebElement checkReading;
    @FindBy(xpath = "//*[@id='hobbiesWrapper']/div[2]/div[3]/label")
    public WebElement checkMusic;
    @FindBy(xpath = "//*[@id='currentAddress']")
    public WebElement inputAddress;
    @FindBy(xpath = "//*[@id=\"state\"]/div/div[2]/div")
    public WebElement inputState;
    @FindBy(xpath = "//*[@id=\"city\"]/div/div[2]/div")
    public WebElement inputCity;
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

    public void completarFormulario(String materia){
        inputSubject.click();
        inputSubject.sendKeys(materia);
        esperar(1);
        inputSubject.sendKeys(Keys.ENTER);

    }

    public void completarFormularioHobbie(String hobbie){
        if(hobbie.equals("Sports")) {
            checkSports.click();
        }else if(hobbie.equals("Reading")){
            checkReading.click();
        }else {
            checkMusic.click();
        }
    }

    public void completarFormulario(String firstName, String lastName, String email, String sexo, String telefono,
                                             String fechaNac){
        String fechaFormat = formatFecha(fechaNac);
        boolean btn = esperarElemento(btnSubmit);
        if(btn){
            inputFirstName.sendKeys(firstName);
            inputLastName.sendKeys(lastName);
            inputEmail.sendKeys(email);
            System.out.println("sexo: "+sexo);
            if(sexo.equals("masculino")){
                radioMale.click();
            }else if(sexo.equals("femenino")){
                radioFemale.click();
            }else {
                radioOther.click();
            }
            inputMobile.sendKeys(telefono + Keys.TAB);
            inputDayOfBirthDate.sendKeys(  fechaFormat + Keys.ENTER);


        }else{
            Assert.fail("Error al esperar elemento del formulario :-(");
        }
    }

    public void completarFormularioDireccion(String direccion){
        inputAddress.sendKeys(direccion);
        inputState.click();
        presionaEnterWindows();
        inputCity.click();
        presionaEnterWindows();
        tomarFoto(test_02, "formulario");
        btnSubmit.click();
    }

    public void evidenciaModal(){
        boolean modal = esperarElemento( btnClose);
        if(modal){
            tomarFoto(test_02, "formulario-modal");
        }else{
            Assert.fail("error al validar popUp");
        }
    }
}
