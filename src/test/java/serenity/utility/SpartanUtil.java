package serenity.utility;

import com.github.javafaker.Faker;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

public class SpartanUtil {

    public static Map<String,Object> getRandomSpartanRequestPayload() {

        Faker faker = new Faker();

        Map<String,Object> payloadMap = new LinkedHashMap<>();
        payloadMap.put("name", faker.name().firstName());
        payloadMap.put("gender", faker.demographic().sex());
        payloadMap.put("phone",faker.number().numberBetween(5000000000L,9999999999L));

        return payloadMap;
    }

//    public static Spartan getRandomSpartanPOJO_Payload(){
//
//        Faker faker = new Faker() ;
//
//        Spartan randomSpartan = new Spartan();
//        randomSpartan.setName(faker.name().firstName());
//        randomSpartan.setGender(faker.demographic().sex());
//        randomSpartan.setPhone(faker.number().numberBetween(5000000000L, 9999999999L));
//
//        return randomSpartan ;
//    }
//
//    public static ResponseSpecification getSuccessResponseSpec(){
//
//        ResponseSpecification resSpec = expect().logDetail(LogDetail.ALL).statusCode(200)
//                                                .contentType( ContentType.JSON );
//
//        return resSpec;
//    }

//    public static ResponseSpecification unauthorizedResponseSpec(){
//
//        ResponseSpecification resSpec = expect().logDetail(LogDetail.ALL).statusCode(401);
//
//        return  resSpec;
//    }
//
//    public static ResponseSpecification forbiddenResponseSpec(){
//
//        ResponseSpecification resSpec = expect().logDetail(LogDetail.ALL).statusCode(403);
//
//        return  resSpec;
//    }
//
//    public static RequestSpecification postRequestSpec(RequestSpecification role){
//
//        RequestSpecification postRequestSpec   = given().spec(role)
//                                                    .contentType( ContentType.JSON)
//                                                    .body( getRandomSpartanPOJO_Payload() );
//
//        return postRequestSpec;
//    }
//
//    public static ResponseSpecification postResponseSpec(){
//
//        ResponseSpecification postResponseSpec = expect().logDetail(LogDetail.ALL)
//                                                        .statusCode(is(201))
//                                                        .contentType(ContentType.JSON)
//                                                        .body("success", is("A Spartan is Born!"))
//                                                        .body("data.id", isA(Integer.TYPE));
//
//        return postResponseSpec;
//    }

    


}
