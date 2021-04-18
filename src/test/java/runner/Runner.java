package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static config.driver.DriverContext.getDriver;
import static config.reportepdf.ReportePdf.*;
import static config.sql.Query.creacionTabla;
import static config.sql.Query.insertarRegistros;


@RunWith(Cucumber.class)
@CucumberOptions(
        tags = {"@Test_01"},
        features = "src/test/java/feature",
        glue = {"stepDefs"},
        monochrome = true)

    public  class Runner  {
    private static WebDriver driver;
    private static ArrayList<String> datosCP;
    private static final String pathProyecto = new File("").getAbsolutePath();


    @BeforeClass()
    public static void setUpClass() throws IOException {
        creaPDF();
        /*data = new DataDriven();
        data.prepararExcel(pathProyecto.concat( pathProyecto.concat( "\\src\\test\\resources\\datos\\DataDriven.xlsx" )),"data","tituloCampoCasosDePrueba");
        datosCP = new ArrayList<String>();*/

        //creacionTabla();
        //insertarRegistros();
    }

    @AfterClass()
    public static void tearDownClass()  {
        driver = getDriver();
        driver.close();
        pdfClose();
    }
}

