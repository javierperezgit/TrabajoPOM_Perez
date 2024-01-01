package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PrivateSiteUser extends ClaseBase {

    //Centralizar Localizadores

    By locatorBtnCerrarSesion= By.xpath("//li/a[@data-drupal-link-system-path='user/logout']");
    By locatorCatalogoApis = By.xpath("//a[@class='nav-link']");
    By locatorBtnCrearApp = By.xpath("//a[@class='btn btn-secondary']");

    //Metodos

    public PrivateSiteUser(WebDriver driver) {
        super(driver);
    }

    public String txtCerrarSesion (){
       return obtenerTexto(locatorBtnCerrarSesion);
    }

    public void CrearApp(){
        click(esperarPorElementoAClickear(locatorBtnCrearApp));
    }

    public void irACatalogoApis(){
        click(esperarPorElementoAClickear(locatorCatalogoApis));
    }





}
