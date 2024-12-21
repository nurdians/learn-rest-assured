package org.example;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestExample {
    public static void main(String[] args) {
        given()
                .header("Content-Type", "application/json")
                .body("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}")
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }
}
