package com.walmart.walmarttestback.services;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    IDiscountService discountService;

    @Autowired
    public ProductService(ProductRepository productRepository, IDiscountService discountService) {
        this.productRepository = productRepository;
        this.discountService = discountService;
    }

    public List<Product> getProducts(String searchQuery) {
        List<Product> products = new ArrayList<>();

        if(searchQuery.matches("\\d+")) {
            Optional<Product> product = productRepository.findById(Integer.parseInt(searchQuery));
            if (product.isPresent()) {
                products.add(product.get());
            }
        }
        else if(searchQuery.length() > 3){
            products = productRepository.findByDescriptionLikeOrBrandLike(searchQuery,searchQuery);
        }

        discountService.apply(searchQuery, products);

        return products;
    }

}
