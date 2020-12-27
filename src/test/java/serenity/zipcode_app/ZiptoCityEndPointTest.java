package serenity.zipcode_app;

import io.restassured.RestAssured;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

@SerenityTest
public class ZiptoCityEndPointTest {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "https://api.zippopotam.us";
    }

    @AfterAll
    public static void tearDown(){
        RestAssured.reset();
        SerenityRest.clear();
    }

    @DisplayName("Testing 1 zipcode and get the result")
    @Test
    public void test1ZipCode(){

        given()
                .pathParam("country","us")
                .pathParam("zipcode","22030").
        when()
                .get("/{country}/{zipcode}");

        Ensure.that("Post code for Fairfax is 22030",
                vRes -> vRes.body("'post code'",is ("22030")))
                .andThat("Place name is Fairfax",
                        vRes -> vRes.body("places[0].'place name'", is("Fairfax")));

    }

    @ParameterizedTest(name = "Iteration number {index} zipcode is {arguments} ")
    @ValueSource(strings ={"22030","22031", "22032", "87123","22035"})
    public void testZipCodes(String zip) {
        System.out.println("zip = " + zip);

        given()
                .pathParam("country","us")
                .pathParam("zipcode",zip).
                when()
                .get("/{country}/{zipcode}");

        Ensure.that("We got successful result",
                v -> v.statusCode(is(200)));
    }

    /**
     * {index} -->> to represent iteration number
     * {arguments} -->>
     * {methodParameterIndexNumber} -->> {0}, {1} ,{2}
     *
     */
    @ParameterizedTest(name = "\uD83D\uDC4DIteration number {index} zipcode is {arguments} 😀 ")
    @ValueSource(strings ={"22030","22031", "22032", "87123","22035"})
    public void testDisplayNameManipulation(String zip) {

    }

    @ParameterizedTest( name = "\uD83C\uDF3AIteration number {index} \uD83C\uDF38Country is {0}\uD83C\uDF37, Zipcode is {1}")
    @CsvFileSource(resources = "/country_zip.csv" , numLinesToSkip = 1)
    public void testCountryZip(String country, int zipcode){

    }
}
