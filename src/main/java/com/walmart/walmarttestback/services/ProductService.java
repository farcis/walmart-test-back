package com.walmart.walmarttestback.services;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.repository.ProductRepository;
import com.walmart.walmarttestback.services.strategies.ISearchStrategy;
import com.walmart.walmarttestback.services.factories.SearchFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IDiscountService discountService;

    @Autowired
    private ISearchStrategy searchStrategy;

    @Autowired
    private SearchFactory searchFactory;

    @Autowired
    public ProductService(ProductRepository productRepository, IDiscountService discountService, ISearchStrategy searchStrategy, SearchFactory searchFactory) {
        this.productRepository = productRepository;
        this.discountService = discountService;
        this.searchStrategy = searchStrategy;
        this.searchFactory = searchFactory;
    }

    public List<Product> getProducts(String searchQuery) {
        if (searchQuery == null || searchQuery.isEmpty()) {
            throw new IllegalArgumentException("Invalid " + searchQuery);
        }

        searchStrategy = searchFactory.getSearchStrategy(searchQuery);
        List<Product> products = searchStrategy.search(searchQuery);
        products = discountService.getProductsWithDiscount(searchQuery, products);

        return products;
    }

}
