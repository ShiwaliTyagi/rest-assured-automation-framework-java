package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import static com.spotify.oauth2.api.Route.BASE_PATH;


public class SpecBuilder {
	static OpenApiValidationFilter validationFilter = new OpenApiValidationFilter(
    	    "src/test/resources/openapi-specs/main-spotify-api.yaml"
    	);
    public static RequestSpecification getRequestSpec(){
    	
        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("BASE_URI")).
     //           setBaseUri("https://api.spotify.com").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(validationFilter).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }

    public static RequestSpecification getAccountRequestSpec(){
        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("ACCOUNT_BASE_URI")).
    //            setBaseUri("https://accounts.spotify.com").
                setContentType(ContentType.URLENC).
              //  addFilter(validationFilter).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
