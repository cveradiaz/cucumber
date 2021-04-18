package config.driver;

import config.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverManager {

    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private WebDriver webDriver;
    ChromeOptions options = new ChromeOptions();


    protected void resolveDriver(String nav) {
        System.out.println("\n[ Sistema operativo >>> " + System.getProperty("os.name").toLowerCase() + " ]\n");
        System.out.println("[ iniciando WebDriver ]");
        String navegador = nav.toLowerCase();
        switch(navegador) {
            case "chrome":
                System.out.println("[ Chrome ]");
                Constants.browser = "Chrome";
                WebDriverManager.chromedriver().setup();
                options.addArguments("--disable-notifications");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--disable-popup-blocking");
                this.webDriver = new ChromeDriver(options);
                //this.capabilities.setBrowserName("Chrome");
                this.webDriver.manage().window().maximize();
                break;
            case "explorer":
                System.out.println("[ Explorer ]");
                Constants.browser = "Explorer";
                this.capabilities.setCapability("enablePersistentHover", false);
                this.capabilities.setCapability("requireWindowFocus", false);
                this.capabilities.setCapability("unexpectedAlertBehaviour", true);
                this.capabilities.setCapability("acceptSslCerts", true);
                this.capabilities.setCapability("ignoreProtectedModeSettings", true);
                this.capabilities.setJavascriptEnabled(true);
                WebDriverManager.iedriver().setup();
                this.webDriver = new InternetExplorerDriver();
                this.capabilities.setBrowserName("Explorer");
                this.webDriver.manage().window().maximize();
                break;
            case "firefox":
                System.out.println("[ Firefox ]");
                Constants.browser = "Firefox";
                WebDriverManager.firefoxdriver().setup();
                this.webDriver = new FirefoxDriver();
                //this.capabilities.setBrowserName("Firefox");
                this.webDriver.manage().window().maximize();
                break;
            case "edge":
                System.out.println("[ Edge ]");
                Constants.browser = "Edge";
                WebDriverManager.edgedriver().setup();
                this.webDriver = new EdgeDriver();
                this.capabilities.setBrowserName("Microsoft Edge");
                this.webDriver.manage().window().maximize();
                break;
            default:
                System.out.println("No es posible lanzar el navegador, no se reconoce el navegador: " + nav);
        }
        /*this.webDriver.get(ambURL);*/
    }

    protected WebDriver getDriver() {
        return this.webDriver;
    }

    protected void setDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

}
