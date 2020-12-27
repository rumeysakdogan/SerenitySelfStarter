package serenity.library_app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serenity.utility.ConfigReader;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.*;

@SerenityTest
public class LibraryTest {

    @Steps
    ConfigReader conf;

//    @BeforeAll
//    public static void setUp(){
//        RestAssured.baseURI = "http://library1.cybertekschool.com";
//        RestAssured.basePath = "/rest/v1" ;
//    }

    @BeforeEach
    public  void setUpEach(){
        RestAssured.baseURI = conf.getProperty("base.url");
        RestAssured.basePath = conf.getProperty("base.path"); ;
    }

    @AfterAll
    public static void tearDown(){
        RestAssured.reset();
        SerenityRest.clear();
    }

    @Test
    public void testLogin(){
        given()
                .log().all()
                .contentType( ContentType.URLENC  )
                .formParam("email", conf.getProperty("librarian.username"))
                .formParam("password", conf.getProperty("librarian.password")).
                when()
                .post("/login") ;

        Ensure.that("Getting successful result",
                vRes -> vRes.statusCode(is(200)));
    }

    @Test
    public void testingReadingConfigFile(){

        System.out.println("conf.getProperty(\"base.url\") = "
                + conf.getProperty("base.url"));

        // mvn clean verify -Denvironment=library2
    }


}
