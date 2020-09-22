package com.walmart.walmarttestback.repository;

import com.walmart.walmarttestback.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,Integer> {
    List<Product> findByDescriptionLikeOrBrandLike(String description, String brand);
}
