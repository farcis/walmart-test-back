package com.walmart.walmarttestback.rest.controllers;

import com.walmart.walmarttestback.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/search/{searchQuery}", method = RequestMethod.GET)
    public Integer getSearchResults(@PathVariable String searchQuery ){
        return searchService.getResponse(searchQuery);
    }
}
