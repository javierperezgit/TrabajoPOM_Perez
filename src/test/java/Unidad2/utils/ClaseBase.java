package Unidad2.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClaseBase {
    //Redefinir las funciones que invocamos a través de la libreria de selenium

    //Atributos
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected JavascriptExecutor js;


    //Metodos
    public ClaseBase(WebDriver driver){
        this.driver=driver;
    }

    //Wrapper Selenium (Envoltorio) Encapsular los metodos

    //click, para poder hacer un clic necesito un By por eso se agrega al argumento
    public void click(By localizador){
        driver.findElement(localizador).click();
    }

    public void click(WebElement elemento){
        elemento.click();
    }

    public String obtenerTexto(WebElement elemento){
       return elemento.getText();
    }

    public String obtenerTexto(By localizador){
        return driver.findElement(localizador).getText();
    }

    public void esperarXSegundos (int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println("Ha ocurrido un error : /");
            throw new RuntimeException(e);
        }
    }

    public void agregarTexto(WebElement elemento, String texto){
        elemento.sendKeys(texto);
    }

    public void agregarTexto(By localizador, String texto){
        driver.findElement(localizador).sendKeys(texto);
    }

    public void agregarCombinacionTeclas(By localizador, Keys combinacion){
        driver.findElement(localizador).sendKeys(combinacion);
    }

    public void agregarCombinacionTeclas(WebElement elemento, Keys combinacion){
        elemento.sendKeys(combinacion);
    }

    public void maximizarBrowser(){
        driver.manage().window().maximize();
    }

    public void cargarURL(String url){
        driver.get(url);
    }

    public void cerrarBrowser(){
        driver.quit();
    }

    public List<WebElement> buscarElementos(By localizador){
        return driver.findElements(localizador);
    }

    public WebElement esperarPresenciaWebElement(By localizador){
        wait = new WebDriverWait(driver, 30);
        WebElement elementoEperado = wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
        return elementoEperado;
    }
    public WebElement esperarPorElementoAClickear(By localizador){
        wait = new WebDriverWait(driver, 30);
        WebElement elementoEsperado = wait.until(ExpectedConditions.elementToBeClickable(localizador));
        return elementoEsperado;
    }

    public void manejoEsperasElementosWeb(int seg){
        this.driver.manage().timeouts().implicitlyWait(seg, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(seg,TimeUnit.SECONDS);
        this.driver.manage().timeouts().pageLoadTimeout(seg,TimeUnit.SECONDS);
    }
    //Método para la conexión con el Driver
    public WebDriver conexionDriver(String browser,String rutaDriver, String property){
        switch (browser){//chrome
            case "chrome":
                System.setProperty(property,rutaDriver);
                this.driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty(property,rutaDriver);
                this.driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty(property,rutaDriver);
                this.driver = new EdgeDriver();
                break;
            default:
                this.driver = null;
        }
        return driver;
    }

    public void seleccionarComboBoxPorTexto(String texto, WebElement elemento){
        Select comboBox = new Select(elemento);
        comboBox.selectByVisibleText(texto);
    }

    public void seleccionarComboBoxPorValue(String value, WebElement elemento){
        Select comboBox = new Select(elemento);
        comboBox.selectByValue(value);
    }

    public void hacerScrollHaciaAbajo(){
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    public void hacerScrollHastaEncontrarElemento(WebElement element){
        js.executeScript("arguments[0].scrollIntoView();",element );
    }

    public boolean estaDesplegado(WebElement elemento){
        try {
            return elemento.isDisplayed();
        }catch (Exception ex){
            System.out.println("Ha ocurrido un error validando elemento web");
            System.out.println(ex.getStackTrace());
            return false;
        }
    }

    public String obtenerAtributoAriaLabel(By localizador){
        return driver.findElement(localizador).getAttribute("aria-label");
    }


    public WebDriver getDriver() {
        return driver;
    }


}
