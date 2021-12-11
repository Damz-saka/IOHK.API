package apiRequests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC002_POST_Request {

    @Test
    void CreateUser(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "morpheus");
        requestParams.put("job", "leader");

        given().
                header("Content-Type", "application/json").
        and().
                contentType(ContentType.JSON).accept(ContentType.JSON).
        and().
                body(requestParams.toJSONString()).
        when().
                post("/users").
        then().
                assertThat().
                statusCode(201).
        and().
                header("content-length", equalTo("84")).
        and().
                header("CF-Cache-Status", equalTo("DYNAMIC")).
                log().all();
    }
}
