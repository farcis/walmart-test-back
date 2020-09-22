package com.walmart.walmarttestback.rest.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"majorVer = 1"})
@ActiveProfiles("dev")
public class SearchControllerIntegrationTest extends BaseRestIntegrationTest {

    private static final String RESOURCE_PATH = "/search/181";
    private static final String RESOURCE_BAD_PATH = "/search/";

    @Test
    public void shouldReturnHttpStatus200WhenNoWantedProductsFound() {
        requestSpecification
                .when()
                .get(RESOURCE_PATH)
                .then()
                .assertThat()
                .statusCode(is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldReturnHttpStatus404WhenUrlIsNotExist() {
        requestSpecification
                .when()
                .get(RESOURCE_BAD_PATH)
                .then()
                .assertThat()
                .statusCode(is(HttpStatus.NOT_FOUND.value()));
    }

}
