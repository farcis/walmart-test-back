package com.walmart.walmarttestback.services;

import com.walmart.walmarttestback.models.Product;

import java.util.List;

public interface IDiscountService {
    List<Product> getProductsWithDiscount(String searchQuery, List<Product> products);
}
