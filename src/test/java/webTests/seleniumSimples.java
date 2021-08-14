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

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

//3 - Classes
public class seleniumSimples {
    //3.1 - Atributos
    WebDriver driver;           // Declarar o objeto selenium WebDriver

    //3.2 - Metodos e funcoes
    @BeforeMethod // @Before ou After Method é executado por metodo executado. ...Test 1 vez por teste geral. ...Class por classe.
    public void iniciar(){
        //A - Incio
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/92/chromedriver.exe");  // Aponta para onde está o driver do chrome.
        driver = new ChromeDriver(); // Instancia o objeto driver como um controlador do Chrome
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
    public void consutalCursoCTFL(){
        driver.get("https://www.iterasys.com.br");
        driver.findElement(By.id("searchtext")).click();
        driver.findElement(By.id("searchtext")).clear();
        driver.findElement(By.id("searchtext")).sendKeys("preparatório ctfl");
        driver.findElement(By.id("btn_form_search")).click();

        assertEquals(driver.findElement(By.cssSelector("div.col-md-6")).getText(), "Cursos › \"preparatório ctfl\"");

        driver.findElement(By.cssSelector("span.comprar")).click();

        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText().toUpperCase(), "PREPARATÓRIO CTFL");
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(), "R$ 169,00");
    }

    //Procurar curso Mantis
    @Test
    public void consultarCursoMantis(){
        //B - Realizar  o teste
        driver.get("https://www.iterasys.com.br"); //.get() navegação simples; .navigator() tem mais funcoes como voltar, refazer, etc. Endereco do site completo com https
        driver.findElement(By.id("searchtext")).click();  //clica no campo de pesquisa (id, name, css-selector, link-text, tag)
        driver.findElement(By.id("searchtext")).clear();  //limpa o campo de pesquisa
        driver.findElement(By.id("searchtext")).sendKeys("mantis"); //Escreve "mantis" no campo

        driver.findElement(By.id("btn_form_search")).click(); //clica na lupa

        //assertTrue(driver.findElement(By.cssSelector("div.col-md-6")).getText().contains("mantis")); assert se contem "mantis"
        assertEquals(driver.findElement(By.cssSelector("div.col-md-6")).getText(), "Cursos › \"mantis\""); // o sinal de mair so ficou igual depois de mudar File>File Properties>File Encoding>Windows-1252

        driver.findElement(By.cssSelector("span.comprar")).click(); // clica no botão matricule-se
        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText(), "Mantis");
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(), "R$ 49,99");

    }
}
