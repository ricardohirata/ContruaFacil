package apiTests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class OrderTests {
    String uri = "https://petstore.swagger.io/v2/store/order";
    String caminhoArqJson = "/src/test/resources/data/order.json";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test(priority = 0)
    public void incluirOrder() throws IOException {
        String jsonBody = lerJson(caminhoArqJson);

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .statusCode(200)
                .log().all()
                .body("id", is(1000))
                .body("petId", is(1000))
                .body("quantity", is(1))
                .body("shipDate", is("2021-08-12T09:28:25.651Z"))
                .body("status", is("approved"))
                .body("complete", is(true))
        ;
    }

}
