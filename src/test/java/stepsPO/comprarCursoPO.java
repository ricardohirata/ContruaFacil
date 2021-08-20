package stepsPO;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class comprarCursoPO {

    @Dado("^que acesso o site da iterasys PO$")
    public void queAcessoOSiteDaIterasysPO() {
        System.out.println("Passo 1 - Acessar o site da iterasys.");
    }

    @Quando("^pesquiso por \"([^\"]*)\" PO$")
    public void pesquisoPorPO(String arg0) {
        System.out.println("Passo 2 - Pesquisar o curso.");
    }

    @E("^clico na lupa PO$")
    public void clicoNaLupaPO() {
        System.out.println("Passo 3 - Clicar na Lupa.");
    }

    @Entao("^vejo a lista de resultado para o curso \"([^\"]*)\" PO$")
    public void vejoAListaDeResultadoParaOCursoPO(String arg0) {
        System.out.println("Passo 4 - Ver a lista de resultador para o curso.");
    }

    @Quando("^clico em Matricule-se PO$")
    public void clicoEmMatriculeSePO() {
        System.out.println("Passo 5 - Clicar em Matricule-se.");
    }

    @Entao("^confirmo o nome do curso como \"([^\"]*)\" e o preco de \"([^\"]*)\" PO$")
    public void confirmoONomeDoCursoComoEOPrecoDePO(String arg0, String arg1) {
        System.out.println("Passo 6 - Confirmo o nome do curso e preco.");
    }
}
