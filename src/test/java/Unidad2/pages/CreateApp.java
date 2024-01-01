package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateApp extends ClaseBase {

    //Centralizar Localizadores

    By locatorTxtCreaApp= By.xpath("//p[contains(text(), 'Para consumir nuestros API Products en ambiente Productivo, es decir, recibir data real, primero debes:')]");


    //Metodos

    public CreateApp(WebDriver driver) {
        super(driver);

    }

    public String txtMsjCreaApp(){
        return obtenerTexto(locatorTxtCreaApp);
    }

}
