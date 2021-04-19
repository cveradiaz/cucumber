package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static drivers.DriverContext.getDriver;
import static drivers.DriverContext.quitDriver;


@RunWith(Cucumber.class)
@CucumberOptions(
        tags = {"@Test_01, @Test_02"},
        features = "src/test/java/feature",
        glue = {"stepDefs"},
        monochrome = true)

    public  class Runner  {
    /*private static WebDriver driver;
    private static ArrayList<String> datosCP;
    private static final String pathProyecto = new File("").getAbsolutePath();


    @Before()
    public static void setUpClass() throws IOException {

    }

    @After()
    public static void tearDown()  {
        quitDriver();
    }*/
}

