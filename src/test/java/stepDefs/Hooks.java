package stepDefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import static config.Constants.test_01;
import static drivers.DriverContext.quitDriver;
import static utils.MetodosGenericos.tomarFoto;

public class Hooks {


    /*@Before
    public void beforeScenario(){
        System.out.println("This will run before the every Scenario");
    }*/
    @Before
    public void beforeScenarioStart(){
        System.out.println("-----------------Comienza la ejecucion del Escenario-----------------");
    }
    @After
    public void afterScenarioFinish(){
        System.out.println("-----------------Fin del Escenario-----------------");
        quitDriver();
    }
    /*@After
    public void afterScenario(){
        System.out.println("This will run after the every Scenario");
    }*/


}
