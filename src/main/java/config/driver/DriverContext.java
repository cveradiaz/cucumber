package config.driver;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverContext {

    private static DriverManager driverManager = new DriverManager();
    private static Navegador tipoNavegador;
    private static String ambienteURL = "";


    public static String getAmbienteURL() {
        return ambienteURL;
    }

    public static void setAmbienteURL(String ambienteURL) {
    }

    public static String getTipoNavegador() {
        return tipoNavegador.toString();
    }

    public static void setUp(String nav) {

        /*setAmbienteURL(ambURL);
        System.out.println(ambURL);*/
        driverManager.resolveDriver(nav);
    }

    public static WebDriver getDriver() {
        return driverManager.getDriver();
    }

    public static void setDriverTimeout(Integer tiempo) {
        driverManager.getDriver().manage().timeouts().implicitlyWait((long)tiempo, TimeUnit.SECONDS);
    }

    public static void quitDriver() {
        driverManager.getDriver().quit();
    }

}
