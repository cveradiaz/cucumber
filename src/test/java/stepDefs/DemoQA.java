package stepDefs;

import com.google.common.collect.Multimap;
import constants.Navegador;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import page.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import static config.Constants.urlDemoQa;
import static config.db.Consultas.*;
import static drivers.DriverContext.setUp;

public class DemoQA {
    HomeDemo homeDemo;
    Registro registro;
    Formulario formulario;
    BookStore bookStore;
    Login login;
    Profile profile;
    static boolean registroUsuario;
    static String nombre="";
    static String apellido="";
    static String correo="";
    static String sexo="";
    static String telefono="";
    static String fechaNac="";
    static String direccion="";
    static Multimap<String, String> libros;

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
            break;
        }
    }

    @Then("validar mensaje de registro del usuario")
    public void validarMensajeDeRegistroDelUsuario() {
            registro.validarAltaUsuario();
    }

    @And("se dirige a la pagina de formulario de practica")
    public void irAlFormulario() {
        formulario = new Formulario();
        formulario.navigateToFormulario();

    }

    @And("ingresa los campos desde la tabla persona")
    public void ingresarDatosDesdeBBDDAlFormulario() {
        List<String> datosPersona = consultarRegistroPersona();
        for (int i = 0; i < datosPersona.size(); i++) {
            nombre = datosPersona.get(i);
            apellido = datosPersona.get(i+1);
            correo =datosPersona.get(i+2);
            sexo = datosPersona.get(i+3);
            telefono = datosPersona.get(i+4);
            fechaNac = datosPersona.get(i+5);

            formulario.completarFormulario( nombre, apellido, correo, sexo, telefono, fechaNac);
            break;
        }
    }

    @And("ingresa las siguientes materias")
    public void ingresarMateriasAlFormulario(DataTable dataTable) {
        List<List<String>> dato = dataTable.raw();
        for (int i = 1; i < dato.size(); i++) {

            formulario.completarFormulario( dato.get( i ).get(0));
        }
    }

    @And("selecciona los siguientes hobbies")
    public void ingresarHobbiesAlFormulario(DataTable dataTable) {
        List<List<String>> dato = dataTable.raw();
        for (int i = 1; i < dato.size(); i++) {

            formulario.completarFormularioHobbie( dato.get( i ).get(0));
        }
    }

    @And("ingresar direccion")
    public void ingresaDireccionAlFormulario() {
        List<String> datosPersona = consultarRegistroPersona();
        for (int i = 6; i < datosPersona.size(); i++) {
            direccion = datosPersona.get(i);
            formulario.completarFormularioDireccion( direccion );
            break;
        }

        formulario.evidenciaModal();
    }

    @And("se dirige a la pagina BookStore")
    public void seDirigeALaPaginaBookStore() {
        bookStore = new BookStore();
        bookStore.navigateToBookStore();
    }

    @And("ingresar al login con datos de la bbdd")
    public void ingresarAlLoginConDatosDeLaBbdd() {
        login = new Login();
        List<String> datosPersona = consultarRegistroLogin();
        for (int i = 0; i < datosPersona.size(); i++) {
            login.logearse(datosPersona.get(i),datosPersona.get(i+1));
            break;
        }

    }

    @And("seleccionar los siguientes libros")
    public void seleccionarLosSiguientesLibros(DataTable dataTable) {
        List<List<String>> dato = dataTable.raw();
        for (int i = 1; i < dato.size(); i++) {
            bookStore.guardarExcel(dato.get(i).get(0));
            libros = bookStore.seleccionarLibro(dato.get(i).get(0));
            Collection<String> values = libros.get(dato.get(i).get(0));
            int valor = consultarRegistroLibro(values.iterator().next(),
                                                dato.get(i).get(0),
                                                values.iterator().next(),
                                                values.iterator().next(),
                                                values.iterator().next(),
                                                values.iterator().next(),
                                                values.iterator().next());
            System.out.println("[Consultando a la bbdd]");
            if(valor == 1){
                System.out.println("\t>>> Registro cuadrado con bbdd");
            }else{
                Assert.fail("\t>>> Error al cuadrar registro");
            }


        }
    }

    @And("ir a profile y obtener evidencia de los libros seleccionados")
    public void irAProfileYObtenerEvidenciaDeLosLibrosSeleccionados() {
        profile = new Profile();
        bookStore.navigateToProfile();
        profile.obtenerEvidencia();
    }
}
