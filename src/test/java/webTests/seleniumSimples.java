//1 - Pacote
package webTests;

//2 - Biblioteca
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

import utils.Evidencias;

//3 - Classes
public class seleniumSimples {
    //3.1 - Atributos
    WebDriver driver;           // Declarar o objeto selenium WebDriver
    Evidencias evidencias;

    //3.2 - Metodos e funcoes
    @BeforeClass // Executa 1 vez apenas, no inicio da execução do script
    public void antesDeTudo(){
        evidencias = new Evidencias();
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/92/chromedriver.exe");  // Aponta para onde está o driver do chrome.
    }

    @BeforeMethod // @Before ou After Method é executado por metodo executado. ...Test 1 vez por teste geral. ...Class por classe.
    public void iniciar(){
        //A - Incio
        driver = new ChromeDriver(); // Instancia o objeto driver como um controlador do Chrome
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MICROSECONDS); //Espera ate 1 minuto, verificando a cada milisegundo. Se o TimeUnit for SECOUND verficará a cada segundo, assim tambem com minuto.
    }

    @AfterMethod
    public void finalizar(){
        //C - Encerramento
        driver.quit(); //encerra o objeto do selenium
    }

    //Procurar curso Preparatório CTFL
    @Test
    public void consutalCursoCTFL() throws IOException {
        String casoDeTeste = "Consultar Curso CTFL";

        evidencias.registrarCSV(casoDeTeste, "Iniciou o Teste.");
        driver.get("https://www.iterasys.com.br");
        evidencias.registrarCSV(casoDeTeste, "Abriu o site.");
        evidencias.print(driver, casoDeTeste,"Parte 1 - Abriu o site");

        driver.findElement(By.id("searchtext")).click();
        evidencias.registrarCSV(casoDeTeste, "Clicou na caixa de pesquisa.");
        driver.findElement(By.id("searchtext")).clear();
        evidencias.registrarCSV(casoDeTeste, "Limpou a caixa de pesquisa.");
        driver.findElement(By.id("searchtext")).sendKeys("preparatório ctfl");
        evidencias.registrarCSV(casoDeTeste, "Escreveu preparatório ctfl na caixa de pesquisa.");
        evidencias.print(driver, casoDeTeste,"Parte 2 - Digitou preparatorio ctfl");
        driver.findElement(By.id("btn_form_search")).click();
        evidencias.registrarCSV(casoDeTeste, "Clicou no botao da lupa.");

        assertEquals(driver.findElement(By.cssSelector("div.col-md-6")).getText(), "Cursos › \"preparatório ctfl\"");
        evidencias.registrarCSV(casoDeTeste, "Confirmou o texto indicativo da pesquisa do curso preparatório ctfl.");
        evidencias.print(driver, casoDeTeste,"Parte 3 - Exibiu a lista de cursos");

        driver.findElement(By.cssSelector("span.comprar")).click();
        evidencias.registrarCSV(casoDeTeste, "Clicou no botão matricule-se.");

        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText().toUpperCase(), "PREPARATÓRIO CTFL");
        evidencias.registrarCSV(casoDeTeste, "Confirmou o nome do curso preparatório ctfl.");
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(), "R$ 169,00");
        evidencias.registrarCSV(casoDeTeste, "Confirmou o preço do curso preparatorio ctfl.");
        evidencias.print(driver, casoDeTeste,"Parte 4 - Exibiu o carrinho de compras");

        evidencias.registrarCSV(casoDeTeste, "Concluiu o teste.");
    }

    //Procurar curso Mantis
    @Test
    public void consultarCursoMantis() throws IOException {
        String casoDeTeste = "Consultar Curso Mantis";

        //B - Realizar  o teste
        evidencias.registrarCSV(casoDeTeste, "Iniciou o Teste.");
        driver.get("https://www.iterasys.com.br"); //.get() navegação simples; .navigator() tem mais funcoes como voltar, refazer, etc. Endereco do site completo com https
        evidencias.registrarCSV(casoDeTeste, "Abriu o site.");
        evidencias.print(driver, casoDeTeste,"Parte 1 - Abriu o site");

        driver.findElement(By.id("searchtext")).click();  //clica no campo de pesquisa (id, name, css-selector, link-text, tag)
        evidencias.registrarCSV(casoDeTeste, "Clicou na caixa de pesquisa.");
        driver.findElement(By.id("searchtext")).clear();  //limpa o campo de pesquisa
        evidencias.registrarCSV(casoDeTeste, "Limpou a caixa de pesquisa.");
        driver.findElement(By.id("searchtext")).sendKeys("mantis"); //Escreve "mantis" no campo
        evidencias.registrarCSV(casoDeTeste, "Escreveu mantis na caixa de pesquisa.");
        evidencias.print(driver, casoDeTeste,"Parte 2 - Digitou Mantis");

        driver.findElement(By.id("btn_form_search")).click(); //clica na lupa
        evidencias.registrarCSV(casoDeTeste, "Clicou no botao da lupa.");

        //assertTrue(driver.findElement(By.cssSelector("div.col-md-6")).getText().contains("mantis")); assert se contem "mantis"
        assertEquals(driver.findElement(By.cssSelector("div.col-md-6")).getText(), "Cursos › \"mantis\""); // o sinal de mair so ficou igual depois de mudar File>File Properties>File Encoding>Windows-1252
        evidencias.registrarCSV(casoDeTeste, "Confirmou o texto indicativo da pesquisa do curso mantis.");
        evidencias.print(driver, casoDeTeste,"Parte 3 - Exibiu a lista de cursos");

        driver.findElement(By.cssSelector("span.comprar")).click(); // clica no botão matricule-se
        evidencias.registrarCSV(casoDeTeste, "Clicou no botão matricule-se.");
        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText(), "Mantis");
        evidencias.registrarCSV(casoDeTeste, "Confirmou o nome do curso mantis.");
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(), "R$ 49,99");
        evidencias.registrarCSV(casoDeTeste, "Confirmou o preço do curso mantis.");
        evidencias.print(driver, casoDeTeste,"Parte 4 - Exibiu o carrinho de compras");

        evidencias.registrarCSV(casoDeTeste, "Concluiu o teste.");
    }
}
