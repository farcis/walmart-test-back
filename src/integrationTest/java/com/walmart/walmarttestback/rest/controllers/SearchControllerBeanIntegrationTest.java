package com.walmart.walmarttestback.rest.controllers;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class SearchControllerBeanIntegrationTest extends BaseRestIntegrationTest  {

    @Autowired
    private SearchController controller;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Product getProduct(int id, String brand, String description) {
        Product product = new Product();
        product.setId(id);
        product.setBrand(brand);
        product.setDescription(description);
        product.setImage("image");
        product.setPrice(1500);
        return product;
    }

    @Test
    public void shouldReturnListProductsWithDiscountSearchIsNumericLenghtLessThanThreePalindrome() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.getSearchResults("br");
        });
    }

    @Test
    public void shouldReturnListProductsWithoutDiscountSearchIsNumericLenghtGreaterThanThree() {
        mongoTemplate.save(getProduct(99998,"brand", "description"));
        List<Product> productList = controller.getSearchResults("99998");

        assertEquals(1500, productList.get(0).getPrice());
        assertEquals(0, productList.get(0).getFinalPrice());
        assertEquals(0, productList.get(0).getDiscount());
    }


    @Test
    public void shouldReturnListProductsWithDiscountSearchIsNumericPalindrome() {
        mongoTemplate.save(getProduct(9999,"brand", "description"));
        List<Product> productList = controller.getSearchResults("9999");

        assertEquals(1500, productList.get(0).getPrice());
        assertEquals(750, productList.get(0).getFinalPrice());
        assertEquals(50, productList.get(0).getDiscount());
    }

    @Test
    public void shouldReturnListProductsWithoutDiscountSearchIsNumericNotPalindrome() {
        mongoTemplate.save(getProduct(99997,"brand", "description"));
        List<Product> productList = controller.getSearchResults("99997");

        assertEquals(1500, productList.get(0).getPrice());
        assertEquals(0, productList.get(0).getFinalPrice());
        assertEquals(0, productList.get(0).getDiscount());
    }

    @Test
    public void shouldReturnListProductsWitDiscountWhenBrandIsPalindrome() {
        mongoTemplate.save(getProduct(183,"asasa", "description"));
        List<Product> productList = controller.getSearchResults("asasa");

        assertEquals(750, productList.get(0).getFinalPrice());
        assertEquals(1500, productList.get(0).getPrice());
        assertEquals(50, productList.get(0).getDiscount());
    }

    @Test
    public void shouldReturnListProductsWitDiscountWhenDescriptionIsPalindrome() {
        mongoTemplate.save(getProduct(184,"asasazz", "asasa"));
        List<Product> productList = controller.getSearchResults("asasa");

        assertEquals(750, productList.get(0).getFinalPrice());
        assertEquals(1500, productList.get(0).getPrice());
        assertEquals(50, productList.get(0).getDiscount());
    }

    @Test
    public void shouldReturnListProductsWithoutDiscountWhenBrandAndDescriptionIsNotPalindrome() {
        mongoTemplate.save(getProduct(185,"asasazz", "asasazz"));
        List<Product> productList = controller.getSearchResults("asasazz");

        assertEquals(1500, productList.get(0).getPrice());
        assertEquals(0, productList.get(0).getFinalPrice());
        assertEquals(0, productList.get(0).getDiscount());
    }
}
