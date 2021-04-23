package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import static drivers.DriverContext.getDriver;
import static utils.MetodosGenericos.esperar;

public class Web {


    public static boolean visualizarObjeto(WebElement elementName, int timeout) {
        try {
            //System.out.println ("Valida si es visible el elemento: [ "+elementName +" ]");
            WebDriverWait wait = new WebDriverWait ( getDriver ( ), timeout);
            wait.until ( ExpectedConditions.visibilityOf (elementName));
            //System.out.println ("\t>>> elemento visible.");
            return true;
        } catch (Exception e) {
            System.out.println ("\t[ Elemento no visible: "+elementName+" ]");
            return false;
        }
    }

    public static boolean visualizarObjeto(WebElement elementName, String vista, int timeout) {
        try {
            //System.out.println ("Valida si es visible el elemento: [ vista: "+vista +" - elemento: "+elementName +" ]");
            WebDriverWait wait = new WebDriverWait ( getDriver ( ), timeout);
            wait.until ( ExpectedConditions.visibilityOf (elementName));
            //System.out.println ("\t>>> elemento visible.\n");
            return true;
        } catch (Exception e) {
            System.out.println ("\t[ Elemento no visible: "+elementName+" ]");
            return false;
        }
    }

    public static boolean visualizarCaptcha(WebElement elementName, int timeout) {
        try {
            //System.out.println ("Valida si es visible el elemento: [ vista: "+vista +" - elemento: "+elementName +" ]");
            WebDriverWait wait = new WebDriverWait ( getDriver ( ), timeout);
            wait.until ( ExpectedConditions.visibilityOf (elementName));
            //System.out.println ("\t>>> elemento visible.\n");
            return true;
        } catch (Exception e) {
            //System.out.println ("\t[ Error en el captcha, se cae por tiempo.");
            return false;
        }
    }

    public static void acceptAlert(){
        try{
            esperar(2);
            WebDriverWait wait = new WebDriverWait(getDriver(), 5);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alertDialog = getDriver().switchTo().alert();
            alertDialog.accept();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static boolean esperarElemento(WebElement objeto, String vista) {
        boolean existe = false;
        int intentos = 0;
        while ((!existe) & intentos < 10) {
            //System.out.println( "Esperando: [ "+(intentos+1) +" de 10 ]" );
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
            //System.out.println( "Esperando: [ "+(intentos+1) +" de 10 ]" );
            existe = visualizarObjeto(objeto, 10);
            if (!existe) {
                intentos++;
            }
        }
        return existe;
    }

    public static boolean esperarElementoCaptcha(WebElement objeto) {
        boolean existe = false;
        int intentos = 0;
        while ((!existe) & intentos < 5) {
            //System.out.println( "Esperando: [ "+(intentos+1) +" de 40 ]" );
            existe = visualizarObjeto(objeto, 5);
            if (!existe) {
                intentos++;
            }
        }
        return existe;
    }

    public static boolean esperaImplicita(WebElement elementName) {
        try {
            //System.out.println ("[Esperando visualizar elemento: "+elementName+" ]");
            getDriver().manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS );
            //System.out.println ("\t>>> Elemento visible.");
            return true;
        } catch (Exception e) {
            System.out.println( "\t>>> Elemento No es visible." );
            return false;
        }
    }

}

