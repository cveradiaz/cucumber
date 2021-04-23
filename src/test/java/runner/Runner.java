package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        tags = {"@Test_01"},
        features = "src/test/java/feature",
        glue = {"stepDefs"},
        monochrome = true)

    public  class Runner  {

}

