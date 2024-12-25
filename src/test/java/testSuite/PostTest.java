package testSuite;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class PostTest {
//    static {
//        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("jalankan ini dulu");
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

    @Test
    public void createPostData() {
        given()
                .headers("Content-Type","application/json")
                .body("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}")
                .log().all()
                .when()
                .post("/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
        ;
    }

    @Test
    public void putPostData() {
        given()
                .headers("Content-Type", "application/json")
                .body("{\"title\": \"foo1\", \"body\": \"bar\", \"userId\": 1}")
                .when()
                .put("posts/1")
                .then()
                .log().all()
                .statusCode(200)
                ;
    }

    @Test
    public void patchPostData() {
//        given()
//                .contentType("application/json")
//                .body("{\"title\": \"foo1\"}")
//                .when()
//                .patch("posts/1")
//                .then()
//                .log().all()
//                .assertThat()
//                .statusCode(200)
//        ;
        Response response = get("/posts/1");
        System.out.println(response.body().jsonPath().getString("title"));
    }
}
