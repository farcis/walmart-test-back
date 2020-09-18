package com.walmart.walmarttestback.rest.controllers;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private ProductService productService;

    @Autowired
    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/search/{searchQuery}", method = RequestMethod.GET)
    public List<Product> getSearchResults(@PathVariable String searchQuery ){
        return productService.getProducts(searchQuery);
    }
}
