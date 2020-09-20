package com.walmart.walmarttestback.services.strategies;

import com.walmart.walmarttestback.models.Product;

import java.util.List;

public interface ISearchStrategy {
    List<Product> search(String searchQuery);
}
