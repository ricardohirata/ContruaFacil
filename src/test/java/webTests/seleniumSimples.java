//1 - Pacote
package webTests;

//2 - Biblioteca
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import utils.Evidencias;

//3 - Classes
public class seleniumSimples {
    //3.1 - Atributos
    WebDriver driver;           // Declarar o objeto selenium WebDriver
    Evidencias evidencias;
    static String datahora = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());

    //3.2 - Metodos e funcoes
    @BeforeMethod // @Before ou After Method é executado por metodo executado. ...Test 1 vez por teste geral. ...Class por classe.
    public void iniciar(){
        //A - Incio
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/92/chromedriver.exe");  // Aponta para onde está o driver do chrome.
        driver = new ChromeDriver(); // Instancia o objeto driver como um controlador do Chrome
        evidencias = new Evidencias();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MICROSECONDS); //Espera ate 1 minuto, verificando a cada milisegundo. Se o TimeUnit for SECOUND verficará a cada segundo, assim tambem com minuto.
    }

    @AfterMethod
    public void finalizar(){
        //C - Encerramento
        System.out.println("FECHANDO.....");
        driver.quit(); //encerra o objeto do selenium
    }

    //Procurar curso Preparatório CTFL
    @Test
    public void consutalCursoCTFL() throws IOException {
        String casoDeTeste = "Consultar Curso CTFL";

        driver.get("https://www.iterasys.com.br");
        evidencias.print(driver, datahora, casoDeTeste,"Parte 1 - Abriu o site");

        driver.findElement(By.id("searchtext")).click();
        driver.findElement(By.id("searchtext")).clear();
        driver.findElement(By.id("searchtext")).sendKeys("preparatório ctfl");
        evidencias.print(driver, datahora, casoDeTeste,"Parte 2 - Digitou preparatorio ctfl");
        driver.findElement(By.id("btn_form_search")).click();

        assertEquals(driver.findElement(By.cssSelector("div.col-md-6")).getText(), "Cursos › \"preparatório ctfl\"");
        evidencias.print(driver, datahora, casoDeTeste,"Parte 3 - Exibiu a lista de cursos");

        driver.findElement(By.cssSelector("span.comprar")).click();

        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText().toUpperCase(), "PREPARATÓRIO CTFL");
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(), "R$ 169,00");
        evidencias.print(driver, datahora, casoDeTeste,"Parte 4 - Exibiu o carrinho de compras");
    }

    //Procurar curso Mantis
    @Test
    public void consultarCursoMantis() throws IOException {
        String casoDeTeste = "Consultar Curso Mantis";

        //B - Realizar  o teste
        driver.get("https://www.iterasys.com.br"); //.get() navegação simples; .navigator() tem mais funcoes como voltar, refazer, etc. Endereco do site completo com https
        evidencias.print(driver, datahora, casoDeTeste,"Parte 1 - Abriu o site");

        driver.findElement(By.id("searchtext")).click();  //clica no campo de pesquisa (id, name, css-selector, link-text, tag)
        driver.findElement(By.id("searchtext")).clear();  //limpa o campo de pesquisa
        driver.findElement(By.id("searchtext")).sendKeys("mantis"); //Escreve "mantis" no campo
        evidencias.print(driver, datahora, casoDeTeste,"Parte 2 - Digitou Mantis");

        driver.findElement(By.id("btn_form_search")).click(); //clica na lupa

        //assertTrue(driver.findElement(By.cssSelector("div.col-md-6")).getText().contains("mantis")); assert se contem "mantis"
        assertEquals(driver.findElement(By.cssSelector("div.col-md-6")).getText(), "Cursos › \"mantis\""); // o sinal de mair so ficou igual depois de mudar File>File Properties>File Encoding>Windows-1252
        evidencias.print(driver, datahora, casoDeTeste,"Parte 3 - Exibiu a lista de cursos");

        driver.findElement(By.cssSelector("span.comprar")).click(); // clica no botão matricule-se
        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText(), "Mantis");
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(), "R$ 49,99");
        evidencias.print(driver, datahora, casoDeTeste,"Parte 4 - Exibiu o carrinho de compras");
    }
}
