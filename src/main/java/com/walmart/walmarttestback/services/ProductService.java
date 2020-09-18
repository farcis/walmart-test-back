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

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(String searchQuery) {
        List<Product> productList = new ArrayList<>();

        if(searchQuery.matches("\\d+")){
            Optional<Product> product = productRepository.findById(Integer.parseInt(searchQuery));
            if (product.isPresent()) {
                productList.add(product.get());
            }
        }
        else if(searchQuery.length() > 3){
            productList = productRepository.findByDescriptionLikeOrBrandLike(searchQuery,searchQuery);
        }

        if(isPalindrome(searchQuery)){
            productList.forEach((item) -> item.setPrice(item.getPrice()/2));
        }

        return productList;
    }

    private boolean isPalindrome(String str) {
        int n = str.length();
        for (int i = 0; i < n/2; ++i) {
            if (str.charAt(i) != str.charAt(n-i-1)){
                return false;
            }
        }
        return true;
    }
}
