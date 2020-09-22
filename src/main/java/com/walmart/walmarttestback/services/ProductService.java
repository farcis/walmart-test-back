package com.walmart.walmarttestback.services;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.services.factories.SearchFactory;
import com.walmart.walmarttestback.services.strategies.ISearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ISearchStrategy searchStrategy;

    @Autowired
    private SearchFactory searchFactory;

    @Autowired
    private IDiscountService discountService;


    @Autowired
    public ProductService(ISearchStrategy searchStrategy, SearchFactory searchFactory, IDiscountService discountService) {
        this.discountService = discountService;
        this.searchStrategy = searchStrategy;
        this.searchFactory = searchFactory;
    }

    public List<Product> getProducts(String searchQuery) {
        if (searchQuery == null || searchQuery.isEmpty()) {
            throw new IllegalArgumentException("Invalid " + searchQuery);
        }

        searchQuery = searchQuery.toLowerCase();

        searchStrategy = searchFactory.getSearchStrategy(searchQuery);
        List<Product> products = searchStrategy.search(searchQuery);
        products = discountService.getProductsWithDiscount(searchQuery, products);

        return products;
    }

}
