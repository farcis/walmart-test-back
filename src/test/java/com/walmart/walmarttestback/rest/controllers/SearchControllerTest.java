package com.walmart.walmarttestback.rest.controllers;

import com.walmart.walmarttestback.services.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private SearchService service;

    private String searchQuery;

    @Test
    public void shouldCallSearchService() {
        searchQuery = randomAlphanumeric(3);
        Integer expectedResponseService = 10;

        when(service.getResponse(eq(searchQuery))).thenReturn(expectedResponseService);
        Integer response = controller.getSearchResults(searchQuery);

        assertThat(response, is(expectedResponseService));
    }
}