package testSuite;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RestAssuredTestNGExample2Test {

    @Test
    public void testGetEndpoint2() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", notNullValue());
    }

    @Test
    public void testPostEndpoint2() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .header("Content-Type", "application/json")
                .body("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}")
                .log().all()
                .when()
                .post("/posts")
                .then()
                .log().all()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }
}
