package utility;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.* ;

public class LibraryUtilities {


    public static String loginAndGetToken(String username, String password){

        Response jsonResponse  = given()
                                        .contentType(ContentType.URLENC)
                                        .formParam("email", username )
                                        .formParam("password", password).
                                when()
                                        .post("/login");

        JsonPath jsonPath = jsonResponse.jsonPath();

        String token = jsonPath.getString("token");

        return  token;
    }

    //getToken(environment)

    public static String loginAndGetToken(String env){

        Response jsonResponse  = given()
                .contentType(ContentType.URLENC)
                .formParam("email", ConfigurationReader.getProperty(env + ".librarian.username") )
                .formParam("password",ConfigurationReader.getProperty(env + ".librarian.password") ).
                        when()
                .post("/login");

        JsonPath jsonPath = jsonResponse.jsonPath();

        String token = jsonPath.getString("token");

        return  token;
    }
}
