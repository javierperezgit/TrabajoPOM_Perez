package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WidgetHipotecario extends ClaseBase {

    //Localizadores

    By locatorBtnVerDocumentacion = By.xpath("//a[@class='btn hipotecario-section-header__documentacion-btn']");

    //Metodo


    public WidgetHipotecario(WebDriver driver) {
        super(driver);
    }

    public void verDocumentacion (){
        click(locatorBtnVerDocumentacion);
    }
}
