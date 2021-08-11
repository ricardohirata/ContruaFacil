package apiTests;

import org.hamcrest.text.StringContainsInOrder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetTests {
    String uri = "https://petstore.swagger.io/v2/pet";
    int petId = 1000;
    String caminhoJson = "src/test/resources/data/pet.json";
    String newCaminhoJson = "src/test/resources/data/newpet.json";

    // Funcoes de apoio (sem @Test)
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test (priority = 0)
    public void incluirPet() throws IOException {

        String jsonBody = lerJson(caminhoJson);
        /*
        * Padrão
        * Given = Dado
        * .When = Quando
        * .Then = Então
         */

        given()                                         //dado
                .contentType("application/json")        //Tipo de conteudo.
                // "text/xml" para webservices sincronos - ex: Correios
                // "application/json" para webservices assincronos - ex: iFood
                // Exemplo: numa ligação as duas pessoas estão disponíveis (sincrono), por mensagem não precisam estar (assincrono)
                .log().all()                            // registrar tudo do envio
                .body(jsonBody)
        .when()                                         // Quando
                .post(uri)     // Comando + endpoint
        .then()                                         // Então
                .log().all()                            // registrar tudo da volta
                .statusCode(200)                        // valida o codigo do estado nativo
                //.body("code", is(200))      //  valida o codigo de estado no json
                .body("id", is(petId))                   //valida o id do animal
                .body("name", is("SNOOPY"))             //valida o nome do animal
                .body("category.name", is("CACHORRO"))       //valida a categoria do animal
                .body("tags.id[0]", is(4))
                .body("tags.name[0]", stringContainsInOrder("primeira"))    //valida se tem a palavra-chave (parte)
                .body("tags.id[1]",is(8))
                .body("tags.name[1]", stringContainsInOrder("segunda"))
                // StringContainsInOrder não é bem visto, pois se tiver animal não vermifugado ele selecionaria, apesar de ser o contrário do que precisa
                //.body("tags.name",contains("vacina em dia."))
        ;
    }

    @Test (priority = 1, dependsOnMethods = "incluirPet")
    public void consultarPet(){

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + Integer.toString(petId))
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("SNOOPY"))
                .body("status", is("available"))
        ;
    }

    @Test (priority = 2,dependsOnMethods = "consultarPet")
    public void alterarPet() throws IOException {
        String jsonBody = lerJson(newCaminhoJson);

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("status", is("sold"))
        ;
    }

    @Test (priority = 3, dependsOnMethods = "alterarPet")
    public void apagarPet(){
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .delete(uri + "/" + Integer.toString(petId))
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message",is(Integer.toString(petId)))
        ;
    }
}
