package com.walmart.walmarttestback.rest.controllers;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class SearchController {

    @Autowired
    private ProductService productService;

    @Autowired
    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/search/{searchQuery}")
    @Transactional(timeout = 240)
    public List<Product> getSearchResults(@PathVariable String searchQuery ){
        return productService.getProducts(searchQuery);
    }
}
