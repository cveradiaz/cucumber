package stepDefs;

import constants.Navegador;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import page.Formulario;
import page.HomeDemo;
import page.Registro;

import java.util.List;

import static config.Constants.urlDemoQa;
import static drivers.DriverContext.setUp;

public class DemoQA {
    HomeDemo homeDemo;
    Registro registro;
    Formulario formulario;

    @Given("^el usuario ingresa a la pagina de DemoQA$")
    public void el_usuario_ingresa_a_la_pagina_de_demoqa() throws Throwable {
        setUp( Navegador.Chrome, urlDemoQa );
        homeDemo = new HomeDemo();
    }

    @And("^va a registrar usuario$")
    public void vaARegistrarUsuario() {
        registro = new Registro();
        registro.navigateToRegister();
    }

    @And("^ingresa los siguientes datos para realizar el registro$")
    public void ingresa_los_siguientes_datos_para_realizar_el_registro(DataTable dataTable) throws Throwable {
        List<List<String>> dato = dataTable.raw();
        for (int i = 1; i < dato.size(); i++) {

            registro.registrarUsuario(  dato.get( i ).get( 1 ),
                                        dato.get( i+1 ).get( 1 ),
                                        dato.get( i+2 ).get( 1 ),
                                        dato.get( i+3 ).get( 1 ));
        }



    }

    @Then("validar mensaje de registro del usuario")
    public void validarMensajeDeRegistroDelUsuario() {

    }

    @And("va a llenar formulario")
    public void vaALlenarFormulario() {
        formulario = new Formulario();
        formulario.navigateToFormulario();
    }

    @And("ingresa los siguientes datos para completar formulario")
    public void ingresaLosSiguientesDatosParaCompletarFormulario() {
    }
}
