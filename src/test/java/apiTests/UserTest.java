package apiTests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class UserTest {
    String uri = "https://petstore.swagger.io/v2/user";
    int userID = 1000;
    String caminhoArqJson = "src/test/resources/data";
    String userName = "ricardohirata";
    String password = "1";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test (priority = 0)
    public void incluirUser() throws IOException {
        String jsonBody = lerJson(caminhoArqJson + "/user.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .statusCode(200)
                .log().all()
                .body("code",is(200))
                .body("type", is("unknown"))
                .body("message", is(Integer.toString(userID)))
        ;
    }

    @Test(priority = 1, dependsOnMethods = "incluirUser")
    public void consultarUser(){
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + userName)
        .then()
                .statusCode(200)
                .log().all()
                .body("id", is(userID))
                .body("username", is(userName))
                .body("firstName", is("Ricardo"))
                .body("lastName", is("Hirata"))
                .body("email", is("ricardo@hirata.com.br"))
        ;
    }

    @Test(priority = 2, dependsOnMethods = "incluirUser")
    public void consultarUserLogin(){
        String token="";
        String mensagem =
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/login?username=" + userName + "&password=" + password)
        .then()
                .statusCode(200)
                .log().all()
                .body("code", is(200))
                .body("type", is("unknown"))
                .extract()
                   .path("message")
        ;
        System.out.printf("A mensagem e %s.\n", mensagem);
        token = mensagem.substring(23);
        System.out.printf("O token e: %s\n\n", token);
    }

    @Test(priority = 3, dependsOnMethods = "consultarUser")
    public void alterarUser() throws IOException {
        String jsonBody = lerJson(caminhoArqJson + "/newuser.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(uri + "/" + userName)
        .then()
                .statusCode(200)
                .log().all()
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(Integer.toString(userID)))
        ;
    }

    @Test(priority = 4, dependsOnMethods = "alterarUser")
    public void deletarUser(){
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .delete(uri + "/" + userName)
        .then()
                .statusCode(200)
                .log().all()
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is("ricardohirata"))
        ;
    }
}
