package apiRequests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

public class TC001_GET_Request {

    @Test
    void ListUsers() {

        baseURI = "https://reqres.in/api";

        given().
                get("/users?page=2").
        then().
                assertThat().
                statusCode(200).
        and().
                body("data.id[1]", equalTo(8)).
        and().
                body("total", equalTo(12)).
        and().
                body("data.first_name", hasItems("Michael", "Lindsay", "Tobias")).
        and().
                header("Content-Type", equalTo("application/json; charset=utf-8")).
                log().all();
    }
}