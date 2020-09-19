package com.walmart.walmarttestback.rest.controllers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BaseRestIntegrationTest {

    @Value("${server.servlet.context-path}")
    protected String contextPath;

    @LocalServerPort
    int port;

    RequestSpecification requestSpecification;
    static final String USER_AGENT = "Spring Testing";

    @Before
    public void parentSetUp() {
        final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setAccept(ContentType.JSON);
        requestSpecBuilder.setPort(port);
        requestSpecBuilder.setBasePath(contextPath);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification =
            given(requestSpecBuilder.build())
                .header(new Header("User-Agent", USER_AGENT));
    }

}
