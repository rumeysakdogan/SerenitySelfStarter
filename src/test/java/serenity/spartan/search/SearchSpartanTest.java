package serenity.spartan.search;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import serenity.utility.SpartanAdminTestBase;

@SerenityTest
public class SearchSpartanTest extends SpartanAdminTestBase {


    @DisplayName("Authenticated user should be able to search")
    @Test
    public  void testSearch(){

        given()
                .auth().basic("admin", "admin")
                .queryParam("nameContains","a")
                .queryParam("gender", "Male").
        when()
                .get("/spartans/search").prettyPeek();

        Ensure.that("Request was successful",
                vRes -> vRes.statusCode( is(200)) )
                .andThat("Response text was json format",
                vRes -> vRes.contentType( ContentType.JSON));

        // chain above ensure you got json result
        // open another ensure
        // make sure you got all names contains a

        Ensure.that("Make sure every item actually contains a",
                vResponse -> vResponse.body("content.name",
                        everyItem( anyOf(containsString("a") , containsString("A")  ) ) ))
                .andThat("Every item gender value is Male",
                        vRes -> vRes.body("content.gender", everyItem( is( "Male" ))));


        ;




    }
}
