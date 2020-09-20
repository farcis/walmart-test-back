package com.walmart.walmarttestback.services.strategies;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchByIdStrategy implements ISearchStrategy {

    @Autowired
    ProductRepository productRepository;

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
