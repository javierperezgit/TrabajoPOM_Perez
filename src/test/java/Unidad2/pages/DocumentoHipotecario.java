package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DocumentoHipotecario extends ClaseBase {

    //Localizadores

    By locatorMensajeErrorAccesoDoc = By.xpath("//div/p[@class='mb-5 col-md-12 texto-permisos mx-auto']");
    By locatorMensajeAccesoDoc = By.xpath("(//p[@class='doc-widget-hipotecario__subtitle'])[2]");

    //Metodos


    public DocumentoHipotecario(WebDriver driver) {
        super(driver);
    }

    public String msjAccesoNoAutorizadoDoc (){
        return obtenerTexto(locatorMensajeErrorAccesoDoc);
    }

    public String msjAccesoAutorizadoDoc (){
        return obtenerTexto(locatorMensajeAccesoDoc);
    }

}
