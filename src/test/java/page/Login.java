package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static drivers.DriverContext.getDriver;
import static utils.MetodosGenericos.esperar;
import static utils.Web.esperarElemento;

public class Login {
    WebDriver driver;

    public Login() {
        this.driver = getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id='login']")
    public WebElement btnLogin;
    @FindBy(xpath = "//*[@id='newUser']")
    public WebElement btnNewUser;
    @FindBy(xpath = "//*[@id='userName']")
    public WebElement inputUserName;
    @FindBy(xpath = "//*[@id='password']")
    public WebElement inputPassword;

    public void
    logearse(String user, String pass){
        esperarElemento(btnLogin);
        btnLogin.click();
        inputUserName.sendKeys(user);
        inputPassword.sendKeys(pass);
        btnLogin.click();
        esperar(3);
    }
}
