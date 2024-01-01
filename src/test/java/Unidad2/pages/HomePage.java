package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ClaseBase {
    //Centralizar Localizadores
    By localizadorBtnLogin = By.xpath("//a[contains(text(),'Iniciar Sesi')]");
    By localizadorCatalogoApis = By.xpath("//a[@class='nav-link']");


    //Metodos
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void irALoguearse(){
        click(esperarPorElementoAClickear(localizadorBtnLogin));
    }

    public void irACatalogoApis(){
        click(esperarPorElementoAClickear(localizadorCatalogoApis));
    }


}
