package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import static drivers.DriverContext.getDriver;

public class Web {


    public static boolean visualizarObjeto(WebElement elementName, int timeout) {
        try {
            System.out.println ("Valida si es visible el elemento: [ "+elementName +" ]");
            WebDriverWait wait = new WebDriverWait ( getDriver ( ), timeout);
            wait.until ( ExpectedConditions.visibilityOf (elementName));
            System.out.println ("\t>>> elemento visible.");
            return true;
        } catch (Exception e) {
            System.out.println ("\t>>> elemento no visible.");
            return false;
        }
    }

    public static boolean visualizarObjeto(WebElement elementName, String vista, int timeout) {
        try {
            System.out.println ("Valida si es visible el elemento: [ vista: "+vista +" - elemento: "+elementName +" ]");
            WebDriverWait wait = new WebDriverWait ( getDriver ( ), timeout);
            wait.until ( ExpectedConditions.visibilityOf (elementName));
            System.out.println ("\t>>> elemento visible.\n");
            return true;
        } catch (Exception e) {
            System.out.println ("\t>>> elemento no visible.\n");
            return false;
        }
    }

    public static void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void cambiarPestanaDerecha(){
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        System.out.println("cantidad pestanas>>> "+tabs.size());
        getDriver().switchTo().window(tabs.get(1));
    }

    public static boolean validaElemento(WebElement objecto){
        boolean estado = false, existe = false;
        boolean elemento = false;
        int intentos = 0;
        do{
            elemento = esperarElemento( objecto );

        }while ( elemento && (intentos < 10) );

        if(!elemento){
            Assert.fail( "Elemento no disponible" );

        }else {
            estado = true;
            Assert.assertTrue( estado );
        }

        return estado;
    }

    public static boolean esperarElemento(WebElement objeto, String vista) {
        boolean existe = false;
        int intentos = 0;
        while ((!existe) & intentos < 10) {
            System.out.println( "Esperando: [ "+(intentos+1) +" de 10 ]" );
            existe = visualizarObjeto(objeto, vista, 10);
            if (!existe) {
                intentos++;
            }
        }
        return existe;
    }

    public static boolean esperarElemento(WebElement objeto) {
        boolean existe = false;
        int intentos = 0;
        while ((!existe) & intentos < 10) {
            System.out.println( "Esperando: [ "+(intentos+1) +" de 10 ]" );
            existe = visualizarObjeto(objeto, 10);
            if (!existe) {
                intentos++;
            }
        }
        return existe;
    }

    public static boolean isEnabled(WebElement element) throws NoSuchElementException {
        System.out.println("Esta el elemento habilitado?:" + element.getAttribute("enabled"));
        return element.getAttribute("enabled").trim().equals("true");
    }

    public static boolean validarEnable(WebElement objeto, int segundos) {
        System.out.println ("Se validará que el objeto:" + objeto + " se encuentre enabled en " + segundos + " segundos.");
        int milisegundos = segundos * 1000;
        boolean res = false;
        for (int i = 0; i < 9; ++i) {
            if (isEnabled (objeto)) {
                System.out.println ("El objeto:" + objeto + " se encuentra enabled.");
                res = true;
                break;
            }
            if (i == 9) {
                System.out.println ("El objeto:" + objeto + " después de " + segundos + " segundos no se encuentra enabled.");
                res = false;
            } else {
                try {
                    Thread.sleep ((long) milisegundos);
                } catch (InterruptedException var6) {
                    Assert.fail ("El Sleep del metodo validarEnable falló, el motivo:" + var6.getMessage ( ));
                }
            }
        }
        return res;
    }

    public static boolean esperaImplicita(WebElement elementName) {
        try {
            System.out.println ("[Esperando visualizar elemento: "+elementName+" ]");
            getDriver().manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS );
            //System.out.println ("\t>>> Elemento visible.");
            return true;
        } catch (Exception e) {
            System.out.println( "\t>>> Elemento No es visible." );
            return false;
        }
    }

    public static boolean esperaElementoImplicita(WebElement objeto) {
        boolean existe = false;
        int intentos = 0;
        while ((!existe) & intentos < 3) {
            System.out.println("[ "+(intentos+1) +" de 3 ]");
            existe = esperaImplicita(objeto);
            //System.out.println("[Esperando objeto " + objeto +"]");
            if (!existe) {
                intentos++;
                System.out.println("\t>>> esperando...");
            } else {
                System.out.println("\t>>> Se encuentra elemento :" + objeto);
                existe = true;
                break;
            }
        }
        return existe;
    }

    public static void waitVisibility(WebDriver driver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitClickable(WebDriver driver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitSelected(WebDriver driver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public static void waitAlert(WebDriver driver, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitInvisibility(WebDriver driver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void elementosInvisibles(WebDriver driver, By element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public static void notTextToBePresentInElement(WebDriver driver, String mensaje, WebElement elemento, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(elemento, mensaje)));
    }

    public static void textToBePresentInElement(WebDriver driver, String mensaje, WebElement elemento, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.textToBePresentInElement(elemento, mensaje));
    }

    public static void waitVisibilitys(WebDriver driver, List<WebElement> elements, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

}

