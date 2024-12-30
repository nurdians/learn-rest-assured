package testSuite;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PostExtendTest extends BaseTest {
    @Test
    public void getPostData() {
        Response response = hitEndpoint(Method.GET,"/posts/1", null);
        response.then().assertThat().statusCode(200);
    }
}
