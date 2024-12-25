package testSuite;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class PostTest {
    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(groups = {"test-post"}, priority = 1)
    public void getPostData() {
        given()
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", notNullValue())
        ;
    }

    @Test
    public void getPostDataThenValidateJson() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/getResponseSchema.json"));

    }

}
