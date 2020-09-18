package com.walmart.walmarttestback.rest.controllers;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    @InjectMocks
    private SearchController controller;

    @Mock
    private ProductService service;

    private String searchQuery;

    @Test
    public void shouldCallSearchService() {
        searchQuery = randomAlphanumeric(3);
        List<Product> expectedResponseService = new ArrayList<>();

        when(service.getProducts(eq(searchQuery))).thenReturn(expectedResponseService);
        List<Product> response = controller.getSearchResults(searchQuery);

        assertThat(response, is(expectedResponseService));
    }
}