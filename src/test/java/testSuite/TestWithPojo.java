package testSuite;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import entities.*;

import static io.restassured.RestAssured.given;

public class TestWithPojo {

    @Test(groups = {"test-pojo"}, priority = 1)
    public void testGetEndpoint2() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Mengirim GET request dan mendeserialisasi response ke POJO
        BookResponse response = given()
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(BookResponse.class);

        // Validasi menggunakan POJO
        assert response.getId() == 1;
        assert response.getUserId() == 1;
        assert response.getTitle() != null;
    }

    @Test(groups = {"test-pojo"}, priority = 2)
    public void testPostEndpoint2() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Membuat POJO untuk request body
        BookRequest request = new BookRequest("foo", "bar", 1);

        // Mengirim POST request dan mendeserialisasi response ke POJO
        BookResponse response = given()
                .header("Content-Type", "application/json")
                .body(request)
                .log().all()
                .when()
                .post("/posts")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(BookResponse.class);

        // Validasi menggunakan POJO
        assert response.getTitle().equals("foo");
        assert response.getBody().equals("bar");
        assert response.getUserId() == 1;
    }
}