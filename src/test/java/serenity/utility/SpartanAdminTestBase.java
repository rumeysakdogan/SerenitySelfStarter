package serenity.utility;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import serenity.utility.ConfigurationReader;

import static io.restassured.RestAssured.*;


public class SpartanAdminTestBase {

    //protected static RequestSpecification adminRequestSpec;

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
       // requestSpecification = given().log().all().auth().basic(ConfigurationReader.getProperty("spartan.admin.username"),
//                                                            ConfigurationReader.getProperty("spartan.admin.password")
//                                                            );
    }

    @AfterAll
    public static void tearDown() {
        RestAssured.reset();
        SerenityRest.clear();
    }
}
