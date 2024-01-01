package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogoSearch extends ClaseBase {

    //centralizar Locators

    By locatorCampoBusqueda = By.id("edit-label");
    By locatorBtnLupaBuscar = By.xpath("//button[contains(@class,'button js-form-submit')]");
    By locatorBtnVerProducto = By.xpath("//a[@class='btn-card button-product']");

    // Metodos

    public CatalogoSearch(WebDriver driver) {
        super(driver);
    }

    public void hacerBusquedaApis(String texto){
        agregarTexto(locatorCampoBusqueda,texto);
        click(locatorBtnLupaBuscar);
        hacerScrollHaciaAbajo();
        click(locatorBtnVerProducto);
    }

}
