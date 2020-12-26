package serenity.spartan;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.*;

import serenity.utility.SpartanUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;


@SerenityTest
public class SimpleSpartanTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://54.87.141.79:8000";
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void cleanUp() {
        reset();
    }

    @DisplayName("Testing GET /api/hello Endpoint")
    @Test
    public void testingHelloEndPoint() {

        when()
                .get("/hello");

//        Serenity's way of generating some steps for verification
        // in the report using Ensure class
        Ensure.that("Make sure endpoint works",
                     response -> response
                                 .statusCode(is(200))
                                 .contentType(ContentType.TEXT)
                                 .body(is("Hello from Sparta"))
                    );

        Ensure.that("Success response was received",
                   thenResponse -> thenResponse.statusCode(is(200))   )
                .andThat("I got text response" ,
                        response -> response .contentType(ContentType.TEXT) )
                .andThat("I got Hello from Sparta",
                        response -> response.body(is("Hello from Sparta")))
                .andThat(": \"Response time was less than 2 sn\"",
                        response -> response.time( lessThan(2L), TimeUnit.SECONDS  ) )
                ;

    }

    @DisplayName("Admin User Should be able to Add Spartan")
    @Test
    public void testAdd1Data(){

        Map<String, Object> payload = SpartanUtil.getRandomSpartanRequestPayload();

        given()
                .auth().basic("admin","admin")
                .contentType( ContentType.JSON)
                .body( payload ).
        when()
                .post("/spartans");

        Ensure.that("Request was successful",
                 response -> response.statusCode(is(201)));
    }


}