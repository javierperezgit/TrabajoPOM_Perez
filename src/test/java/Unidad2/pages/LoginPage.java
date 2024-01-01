package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends ClaseBase {

    //Centralizar localizadores
    By locatorTxtUserName = By.xpath("//input[@id='edit-name']");
    By locatorTxtPassword = By.xpath("//input[@id='edit-pass']");
    By locatorBtnIniciarSesion = By.id("edit-submit");
    By locatorTxtMensajeError = By.xpath("//*[@id=\"page\"]/div[1]/aside/div[2]/div");





    //Metodos
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void iniciarSesion(String usuario, String password){
      agregarTexto(esperarPresenciaWebElement(locatorTxtUserName),usuario);
      agregarTexto(esperarPresenciaWebElement(locatorTxtPassword),password);
      click(esperarPorElementoAClickear(locatorBtnIniciarSesion));
    }

    public void login (){
        click(esperarPorElementoAClickear(locatorBtnIniciarSesion));
    }

    public String ValidarMensajeError(){
        String [] var1 = obtenerTexto(locatorTxtMensajeError).split("\\n");
        String var2 = var1[2];
        return var2;

    }

}
