package stepDefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import static drivers.DriverContext.quitDriver;

public class Hooks {


    @Before
    public void beforeScenario(){
        System.out.println("This will run before the every Scenario");
    }
    @Before
    public void beforeScenarioStart(){
        System.out.println("-----------------Start of Scenario-----------------");
    }
    @After
    public void afterScenarioFinish(){
        System.out.println("-----------------End of Scenario-----------------");
        quitDriver();
    }
    @After
    public void afterScenario(){
        System.out.println("This will run after the every Scenario");
    }


}
