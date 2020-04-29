package com.automation.test.libraries.web.api.utils;

import com.automation.framework.core.web.ui.object.Wrapper;
import com.automation.test.libraries.web.api.models.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PostMethods {
    Wrapper baseObj = new Wrapper();
    public Post getPostObjectByID(String postObjectID) {
        String baseURL = "https://jsonplaceholder.typicode.com/posts/";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Response response = given().relaxedHTTPSValidation().auth().none().contentType(JSON)
                .when().log().headers().get(baseURL + postObjectID);

        JsonObject jsonObj = new JsonParser().parse(response.body().prettyPrint()).getAsJsonObject();

        return gson.fromJson(jsonObj, Post.class);
    }

    public Post createPostObject(Post postObj) {
        Wrapper baseObj = new Wrapper();
        baseObj.info("Creating Post Object");
        String baseURL = "https://jsonplaceholder.typicode.com/posts";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Response response = given().relaxedHTTPSValidation().auth().none().contentType(JSON)
                .body(gson.toJson(postObj))
                .when().log().headers().post(baseURL);

        JsonObject jsonObj = new JsonParser().parse(response.body().prettyPrint()).getAsJsonObject();

        return gson.fromJson(jsonObj, Post.class);
    }
    //public boolean verifyPostObject(int userID, String title, String body, String expectedUserID, String expectedTitle, String expectedBody)
    public boolean verifyPostObject(Post postObj, String expectedUserID, String expectedTitle, String expectedBody)
    {
        baseObj.info("Verify Post object");
        if(postObj.getUserId() == Integer.parseInt(expectedUserID)  && postObj.getTitle().equals(expectedTitle)  && postObj.getBody().equals(expectedBody))
        {
            baseObj.pass("Post Object information matches expected information");
            return true;
        }
        else {
            baseObj.fail("Post Object information does not match expected information");
            return false;
        }
    }


}
