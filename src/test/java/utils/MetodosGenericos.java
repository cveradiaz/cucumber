package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static drivers.DriverContext.getDriver;

public class MetodosGenericos {

    public static int getResponseCode(String urlString){
        URL url = null;
        int code=0;
        try {
            url = new URL(urlString);
            HttpURLConnection huc = (HttpURLConnection)url.openConnection();
            huc.setRequestMethod("GET");
            huc.connect();
            code = huc.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static void tomarFoto(String casoPrueba, String nombre) {
        esperar(2);
        TakesScreenshot scrShot = ((TakesScreenshot)getDriver());
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(casoPrueba+nombre+".png");
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatFecha(String fecha){
        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada="";
        try {
            fechaFormateada = myFormat.format(fromUser.parse(fecha));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("format fecha: "+fechaFormateada);
        return fechaFormateada;
    }

    public static void esperar(int tiempo){
        try{
            Thread.sleep( tiempo * 1000 );
        }catch (InterruptedException io){
            System.out.println(">>> "+io);
        }
    }

    public static void presionaEnterWindows() {
        esperar(1);
        Robot r = null;
        try {
            r = new Robot();
            r.keyPress( KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
            esperar(1);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
