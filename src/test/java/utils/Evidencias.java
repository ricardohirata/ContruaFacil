package utils;

import com.opencsv.CSVWriter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Evidencias {
    static String dataHoraSimples = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());

    public void print(WebDriver driver, String casoDeTeste, String nomePrint) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(foto, new File("target/prints/" + dataHoraSimples + "/" + casoDeTeste+ "/" + nomePrint + ".png"));
    }

    //Cria o arquivo de log no inicio da execucao
    public void iniciaLogCSV() throws IOException {
        String[] cabecalho = {"data e hora", "caso de teste", "mensagem"};
        Writer escritor = Files.newBufferedWriter(Paths.get("target/logs/log - " + dataHoraSimples + ".csv"));
        CSVWriter escritorCSV = new CSVWriter(escritor);

        escritorCSV.writeNext(cabecalho);

        escritorCSV.flush();
        escritor.close();
    }

    //Gravar cada linha no log
    public void registrarCSV(String casoDeTeste, String mensagem) throws IOException {
        String dataHora = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss.SSS]").format(Calendar.getInstance().getTime());

        String[] linha = {dataHora, casoDeTeste, mensagem};
        FileWriter escritor = new FileWriter("target/logs/log - " + dataHoraSimples + ".csv", true);
        CSVWriter escritorCSV = new CSVWriter(escritor);

        escritorCSV.writeNext(linha);

        escritorCSV.flush();
        escritor.close();
    }
}
