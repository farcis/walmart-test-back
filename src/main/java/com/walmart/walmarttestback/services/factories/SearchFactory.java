package com.walmart.walmarttestback.services.factories;

import com.walmart.walmarttestback.services.strategies.ISearchStrategy;
import com.walmart.walmarttestback.services.strategies.SearchByAllFieldsStrategy;
import com.walmart.walmarttestback.services.strategies.SearchByIdStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchFactory {
    @Autowired
    SearchByIdStrategy searchByIdStrategy;

    @Autowired
    SearchByAllFieldsStrategy searchByAllFieldsStrategy;

    @Autowired
    public SearchFactory(
            SearchByIdStrategy searchByIdStrategy,
            SearchByAllFieldsStrategy searchByAllFieldsStrategy){
        this.searchByIdStrategy = searchByIdStrategy;
        this.searchByAllFieldsStrategy = searchByAllFieldsStrategy;
    }

    public ISearchStrategy getSearchStrategy(String searchQuery){
        ISearchStrategy searchStrategy;

        if(searchQuery.matches("\\d+")) {
            searchStrategy = searchByIdStrategy;
        }
        else if(searchQuery.length() > 3){
            searchStrategy = searchByAllFieldsStrategy;
        }
        else throw new IllegalArgumentException("Invalid " + searchQuery);

        return searchStrategy;
    }
}
