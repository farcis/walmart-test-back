package com.walmart.walmarttestback.services;

import com.walmart.walmarttestback.models.Product;

import java.util.List;

public interface IDiscountService {
    void apply(String searchQuery, List<Product> products);
}
