package stepDefs;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import page.Home;
import page.HomeDemo;
import page.Registro;

import static config.driver.DriverContext.setUp;
import static config.reportepdf.ReportePdf.nombreClase;

public class DemoQA {
    HomeDemo homeDemo;
    Registro registro;

    @Given("^el usuario ingresa a la pagina de DemoQA$")
    public void el_usuario_ingresa_a_la_pagina_de_demoqa() throws Throwable {
        nombreClase(this.getClass().getSimpleName());
        setUp( "chrome" );
        homeDemo = new HomeDemo();
        homeDemo.navigateToHomePage();
    }

    @And("^va a registrar usuario$")
    public void vaARegistrarUsuario() {
        registro = new Registro();
        registro.navigateToRegister();
    }

    @And("^ingresa los siguientes datos para realizar el registro$")
    public void ingresa_los_siguientes_datos_para_realizar_el_registro(DataTable dataTable) throws Throwable {
        throw new PendingException();
    }


}
