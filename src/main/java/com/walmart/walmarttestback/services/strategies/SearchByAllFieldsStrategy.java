package com.walmart.walmarttestback.services.strategies;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("searchByAllFieldsStrategyClass")
public class SearchByAllFieldsStrategy implements ISearchStrategy {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    public SearchByAllFieldsStrategy(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> search(String searchQuery) {
        return productRepository.findByDescriptionLikeOrBrandLike(searchQuery,searchQuery);
    }
}
