package com.walmart.walmarttestback.services.factories;

import com.walmart.walmarttestback.services.strategies.ISearchStrategy;
import com.walmart.walmarttestback.services.strategies.SearchByAllFieldsStrategy;
import com.walmart.walmarttestback.services.strategies.SearchByIdStrategy;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchFactory {
    @Autowired
    SearchByIdStrategy searchByIdStrategy;

    @Autowired
    SearchByAllFieldsStrategy searchByAllFieldsStrategy;

    public ISearchStrategy getSearchStrategy(String searchQuery){
        ISearchStrategy searchStrategy;

        if(searchQuery.matches("\\d+")) {
            searchStrategy = new SearchByIdStrategy();
        }
        else if(searchQuery.length() > 3){
            searchStrategy = new SearchByAllFieldsStrategy();
        }
        else throw new IllegalArgumentException("Invalid " + searchQuery);

        return searchStrategy;
    }
}
