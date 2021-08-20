package webTests;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class comprarCursoCS {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void inciar(){
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/92/chromedriver.exe");
        driver = new ChromeDriver();
        // Espera dinâmica explicita. Uma espera mais inteligente, para situações com mais possibilidades. Espera somente quando eu chamo.
        wait = new WebDriverWait(driver, 5, 1);
        //driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MICROSECONDS); // Valido para todos
        driver.manage().window().maximize(); //maximizar a janela
        System.out.println("0 - Antes do teste iniciar.");
    }

    @After
    public void finalizar() {
        driver.quit();
        System.out.println("Z - Depois do teste finalizar.");
    }

    @Dado("^que acesso o site da iterasys$")
    public void queAcessoOSiteDaIterasys(){
        driver.get("https://www.iterasys.com.br");
        System.out.println("1 - Acessou o site.");
    }

    @Quando("^pesquiso por \"([^\"]*)\"$")
    public void pesquisoPor(String arg0) {
        driver.findElement(By.id("searchtext")).sendKeys(Keys.chord(arg0)); //Escreve o nome do curso letra por letra
        System.out.println("2 - Digitou o nome do curso como" + arg0);

    }

    @E("^clico na lupa$")
    public void clicoNaLupa() {
        driver.findElement(By.id("btn_form_search")).click();
        System.out.println("3 - Clicou na lupa.");
    }

    @Entao("^vejo a lista de resultado para o curso \"([^\"]*)\"$")
    public void vejoAListaDeResultadoParaOCurso(String arg0)  {
        //ExpectedConditions.textToBePresentInElement: se o elemento ja tem qualquer texto
        //ExpectedConditions.textToBe: Espera até que o elemento tenha o texto esperado.
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h3"),"Cursos › \"" + arg0 + "\""));
        assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Cursos › \"" + arg0 + "\"");
        System.out.println("4 - Exibiu a lista de resultado para o curso: " + arg0);
    }

    @Quando("^clico em Matricule-se$")
    public void clicoEmMatriculeSe() {
        driver.findElement(By.cssSelector("span.comprar")).click();
        System.out.println("5 - Clicou em matricule-se.");
    }

    @Entao("^confirmo o nome do curso como \"([^\"]*)\" e o preco de \"([^\"]*)\"$")
    public void confirmoONomeDoCursoComoEOPrecoDe(String arg0, String arg1)  {
        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText(), arg0);
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(),arg1);
        System.out.println("6 - Confirmou o nome do curso como " + arg0 + " e o preco do curso como " + arg1);
    }

    @E("^pressiono Enter$")
    public void pressionoEnter() {
        driver.findElement(By.id("searchtext")).sendKeys(Keys.ENTER);
        System.out.println("3a - Pressiono Enter.");
    }

    @Quando("^clico na imagem de um curso$")
    public void clicoNaImagemDeUmCurso() {
        driver.findElement(By.xpath("//body/main[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/a[1]/span[3]")).click();
        System.out.println("3 - Clicou na imagem do curso");
    }

    @Entao("^vejo apagina com detalhes do curso$")
    public void vejoApaginaComDetalhesDoCurso() {
        wait.until(ExpectedConditions.titleIs("Mantis - Iterasys"));
        assertEquals(driver.getTitle(), "Mantis - Iterasys");
        System.out.println("4 - Ver os detalhes do curso");
    }

    @E("^clico no botao Ok do popup da LGPD$")
    public void clicoNoBotaoOkDoPopupDaLGPD() {
        wait.until(ExpectedConditions.textToBe(By.id("cookieconsent:desc"), "Este site usa cookies para melhorar sua experiência. Política de Privacidade"));
        driver.findElement(By.cssSelector("div.cc-compliance")).click();
        System.out.println("2 - Clico no popup da LGPD");
    }

    @Quando("^movo o mouse ate a imagem do curso$")
    public void movoOMouseAteAImagemDoCurso() {
        //Mover o mouse ate o curso antes de clicar
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//body/main[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/a[1]/span[3]"))).perform();
    }

    @E("^clico na imagem saiba mais de um curso$")
    public void clicoNaImagemSaibaMaisDeUmCurso() {
        driver.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/a[1]/span[1]")).click();
        System.out.println("3 - Clicou na imagem saiba mais do curso");
    }
}
