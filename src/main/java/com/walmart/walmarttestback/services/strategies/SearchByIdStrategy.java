package com.walmart.walmarttestback.services.strategies;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Primary
@Qualifier("searchByIdStrategyClass")
public class SearchByIdStrategy implements ISearchStrategy {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    public SearchByIdStrategy(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> search(String searchQuery) {
        List<Product> products = new ArrayList<>();
        Optional<Product> productsOptional = productRepository.findById(Integer.parseInt(searchQuery));
        if (productsOptional.isPresent()) {
            products.add(productsOptional.get());
        }

        return products;
    }
}
