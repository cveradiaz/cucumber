package page;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import excel.AccionesExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;

import static config.Constants.*;
import static drivers.DriverContext.getDriver;
import static utils.MetodosGenericos.esperar;
import static utils.MetodosGenericos.tomarFoto;
import static utils.Web.*;

public class BookStore {
    WebDriver driver;

    public BookStore() {
        this.driver = getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id='login']")
    public WebElement btnLogin;
    @FindBy(xpath = "//*[@id='see-book-Git Pocket Guide']")
    public WebElement libroGitPocketGuide;
    @FindBy(xpath = "//*[contains(text(),'Back To Book Store')]")
    public WebElement btnBackToStore;
    @FindBy(xpath = "//*[contains(text(),'Add To Your Collection')]")
    public WebElement btnAddToYouCollection;
    @FindBy(xpath = "//*[@id='ISBN-wrapper']/div[2]/label")
    public WebElement lblIsbn;
    @FindBy(xpath = "//*[@id='title-wrapper']/div[2]/label")
    public WebElement lblTitle;
    @FindBy(xpath = "//*[@id='subtitle-wrapper']/div[2]/label")
    public WebElement lblSubTitle;
    @FindBy(xpath = "//*[@id='author-wrapper']/div[2]/label")
    public WebElement lblAutor;
    @FindBy(xpath = "//*[@id='publisher-wrapper']/div[2]/label")
    public WebElement lblPublisher;
    @FindBy(xpath = "//*[@id='pages-wrapper']/div[2]/label")
    public WebElement lblTotalPages;
    @FindBy(xpath = "//*[@id='website-wrapper']/div[2]/label")
    public WebElement lblWebSite;
    @FindBy(xpath = "//span[contains(text(),'Profile')]")
    public WebElement btnProfile;




    public void navigateToBookStore() {

        this.driver.navigate().to( urlBooks );
        boolean elemento = visualizarObjeto(btnLogin, 5);
        if(elemento){
            System.out.println("[ BookStore ]");
        }
    }

    public Multimap<String, String> guardarDatoslibro(String libro){
        Multimap<String, String> datosLibro = ArrayListMultimap.create();
        datosLibro.put(libro, lblIsbn.getText());
        datosLibro.put(libro, lblTitle.getText());
        datosLibro.put(libro, lblSubTitle.getText());
        datosLibro.put(libro, lblAutor.getText());
        datosLibro.put(libro, lblPublisher.getText());
        datosLibro.put(libro, lblTotalPages.getText());
        datosLibro.put(libro, lblWebSite.getText());
        return datosLibro;
    }

    public Multimap<String, String> seleccionarLibro(String libro){

        WebElement book = driver.findElement( By.xpath( "//*[contains(text(),'"+ libro +"')]" ) );
        boolean existe = esperarElemento(book);
        if(existe){
            book.click();
        }else {
            Assert.fail("Error al seleccionar libro: "+libro);
        }
        //esperar(4);
        Multimap<String, String> datosLibro = guardarDatoslibro(libro);
        if(btnAddToYouCollection.isDisplayed()){
            btnAddToYouCollection.click();
            acceptAlert();
            System.out.println("[ Libro agregado: "+libro+" ]");
        }
        esperaImplicita(btnBackToStore);
        btnBackToStore.click();
        return datosLibro;
    }

    public void guardarExcel( String libro){
        AccionesExcel excel = new AccionesExcel();
        String[] aLibros;
        ArrayList<String> libros =  new ArrayList<String>();
        String libroExecel = "libros";
        String hoja = "datos";
        String [] cabecera = {"Libros Seleccionados"};
        libros.add(libro);
        aLibros = libros.toArray(new String[libros.size()]);
        boolean existe = excel.existeExcel("libros");
        System.out.println(existe);
        if(existe){
            excel.agregarLineaExcel(libroExecel,aLibros);
        }else{
            excel.crearNuevoExcel(libroExecel,hoja,cabecera);
            excel.agregarLineaExcel(libroExecel,aLibros);
        }

    }

    public void navigateToProfile() {
        boolean profile = visualizarObjeto(btnProfile, 5);
        if(profile){
            System.out.println("[ Profile ]");
            scrollDown();
            btnProfile.click();
        }else{
            Assert.fail("Algo paso al seleccionar Profile");
        }

    }
}
