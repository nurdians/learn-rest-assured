package testSuite;

import entities.UserRequest;
import entities.UserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTest {

    @Test(groups = {"test1"})
    public void getAllUsers(){
        given()
                .baseUri("https://reqres.in/")
                .when()
                .get("/api/users?page=1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("page", equalTo(1));
    }

    @Test
    public void postUserData(){
        given()
                .baseUri("https://reqres.in/")
                .contentType("application/json")
                .body("{\"name\": \"iwan\", \"job\" : \"tester\"}")
                .log().body()
                .when()
                .post("/api/users")
                .then()
                .log().body()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("iwan"))
                .body("job", equalTo("tester"))
        ;
    }

    @Test
    public void postUserData2(){
        UserRequest userRequest = new UserRequest("iwan", "tester");

//        RequestSpecification requestSpecification

        UserResponse userResponse = given()
                .baseUri("https://reqres.in/")
                .contentType("application/json")
                .body(userRequest)
                .log().body()
                .when()
                .post("/api/users")
                .as(UserResponse.class)
//                .then()
//                .log().body()
//                .a
//                .assertThat()
//                .statusCode(201)
//                .body("name", equalTo("iwan"))
//                .body("job", equalTo("tester"))
        ;

        Assert.assertEquals(userResponse.getName(),"iwan");
        Assert.assertEquals(userResponse.getJob(),"tester");
    }

    @Test
    public void userEndToEndTest(){
        String name = generateRandomString();
        String job = generateRandomString();

        UserRequest userRequest = new UserRequest(name, job);
        UserResponse userResponse = given()
                .baseUri("https://reqres.in/")
                .contentType("application/json")
                .body(userRequest)
                .log().body()
                .when()
                .post("/api/users")
                .as(UserResponse.class);

        String id = userResponse.getId();
        String endpoint = "/api/users/" + id;

        given()
                .baseUri("https://reqres.in")
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .assertThat()
                .body("name", equalTo(name))
//                .body("job", equalTo(job))
                ;
//                .body("data.id", hasItemInArray(id));
    }

    private String generateRandomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        System.out.println(generatedString);

        return  generatedString;
    }
}
