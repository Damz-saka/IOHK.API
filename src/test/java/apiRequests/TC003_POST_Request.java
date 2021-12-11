package apiRequests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_POST_Request {

    @Test
    void RegisterUnsuccessful(){

        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "sydney@fife");
        httpRequest.header("Content-Type", "application/json");
        httpRequest.contentType(ContentType.JSON).accept(ContentType.JSON);
        httpRequest.body(requestParams.toJSONString());

        Response response = httpRequest.request(Method.POST, "/register");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" +responseBody);
        int statusCode = response.getStatusCode();
        System.out.println("Status Code is:" +statusCode);
        Assert.assertEquals(statusCode, 400);
        String Error = response.jsonPath().get("error");
        Assert.assertEquals(Error, "Missing password");
    }
}
