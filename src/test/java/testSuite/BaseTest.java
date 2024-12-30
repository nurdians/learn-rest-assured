package testSuite;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseTest {
//    RequestSpecification requestSpecification = given().baseUri("");
    protected Response hitEndpoint(Method method, String endpoint, Object requestBody){
        RequestSpecification requestSpecification = given().baseUri("https://jsonplaceholder.typicode.com").contentType("application/json");
        Response response;
        if (requestBody != null) {
            requestSpecification.body(requestBody);
        }

        switch (method){
            case GET:
                response = requestSpecification.when().get(endpoint);
                break;
            case POST:
                response = requestSpecification.when().post(endpoint);
                break;
            case PUT:
                response = requestSpecification.when().put(endpoint);
                break;
            case PATCH:
                response = requestSpecification.when().patch(endpoint);
                break;
            case DELETE:
                response = requestSpecification.when().delete(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Invalid method");
        }

        return response;
    }
}
