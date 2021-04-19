package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static drivers.DriverContext.getDriver;

public class HomeDemo {

    WebDriver driver;

    public HomeDemo() {
        this.driver = getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@class='card mt-4 top-card'][1]")
    public WebElement btnElements;


}
