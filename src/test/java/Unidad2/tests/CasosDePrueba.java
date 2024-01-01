package Unidad2.tests;

import Unidad2.pages.*;
import Unidad2.utils.DataDriven;
import Unidad2.utils.PropertiesManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CasosDePrueba {

    public static String corregirEncoding(String textoIncorrecto) {
        // Convertir la cadena incorrecta de la codificación ISO-8859-1 a UTF-8
        byte[] bytes = textoIncorrecto.getBytes(StandardCharsets.ISO_8859_1);
        String textoCorregido = new String(bytes, StandardCharsets.UTF_8);
        return textoCorregido;
    }

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private HomePage homePage;
    private LoginPage loginPage;
    private PrivateSiteUser privateSiteUser;
    private CreateApp createApp;
    private CatalogoSearch catalogoSearch;
    private WidgetHipotecario widgetHipotecario;
    private DocumentoHipotecario documentoHipotecario;

    private String browser = PropertiesManager.obtenerProperty("browser");
    private String rutaDriver = PropertiesManager.obtenerProperty("rutaDriver");
    private String url = PropertiesManager.obtenerProperty("url");
    private String propertyDriver = PropertiesManager.obtenerProperty("propertyDriver");
    private ArrayList<String> datosPrueba;


    @BeforeEach
    public void preparacionTests(){
        datosPrueba = new ArrayList<String>();//se crea un arreglo vacio
        homePage = new HomePage(driver);
        homePage.conexionDriver(browser,rutaDriver,propertyDriver);
        homePage.manejoEsperasElementosWeb(10);
        homePage.cargarURL(url);
        homePage.maximizarBrowser();

        loginPage = new LoginPage(homePage.getDriver());
        privateSiteUser= new PrivateSiteUser(homePage.getDriver());
        createApp = new CreateApp(homePage.getDriver());
        catalogoSearch = new CatalogoSearch(homePage.getDriver());
        widgetHipotecario = new WidgetHipotecario(homePage.getDriver());
        documentoHipotecario = new DocumentoHipotecario(homePage.getDriver());
    }

    @Test
    public void CP001_Login_fallido(){
    datosPrueba = DataDriven.prepararData("CP001_Login_fallido");
    homePage.manejoEsperasElementosWeb(10);
    homePage.irALoguearse();
    loginPage.iniciarSesion(datosPrueba.get(1),datosPrueba.get(2));
    Assertions.assertEquals(datosPrueba.get(3),loginPage.ValidarMensajeError());
    }

    @Test
    public void CP002_Login_correcto(){
        datosPrueba = DataDriven.prepararData("CP002_Login_correcto");
        homePage.irALoguearse();
        loginPage.iniciarSesion(datosPrueba.get(1),datosPrueba.get(2));
        Assertions.assertEquals(datosPrueba.get(3).toLowerCase(), privateSiteUser.txtCerrarSesion().toLowerCase());
    }

    @Test
    public void CP003_ReglaCrearAplicacion(){
        datosPrueba = DataDriven.prepararData("CP003_ReglaCrearAplicacion");
        homePage.irALoguearse();
        loginPage.iniciarSesion(datosPrueba.get(1),datosPrueba.get(2));
        privateSiteUser.CrearApp();
        Assertions.assertEquals(datosPrueba.get(3),createApp.txtMsjCreaApp());
    }

    @Test
    public void CP004_ConsultarDocApiSinLogin(){
        datosPrueba = DataDriven.prepararData("CP004_ConsultarDocApiSinLogin");
        homePage.irACatalogoApis();
        catalogoSearch.hacerBusquedaApis(datosPrueba.get(1));
        widgetHipotecario.verDocumentacion();
        Assertions.assertEquals(datosPrueba.get(2),documentoHipotecario.msjAccesoNoAutorizadoDoc());
    }

    @Test
    public void CP005_ConsultarDocApiConLogin(){
        datosPrueba = DataDriven.prepararData("CP005_ConsultarDocApiConLogin");
        homePage.irALoguearse();
        loginPage.iniciarSesion(datosPrueba.get(1),datosPrueba.get(2));
        privateSiteUser.irACatalogoApis();
        catalogoSearch.hacerBusquedaApis(datosPrueba.get(3));
        widgetHipotecario.verDocumentacion();
        Assertions.assertEquals(datosPrueba.get(4), documentoHipotecario.msjAccesoAutorizadoDoc());
    }


    @AfterEach
    public void postEjecucion(){
        homePage.cerrarBrowser();
    }

}
