package com.walmart.walmarttestback.services;

import com.walmart.walmarttestback.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PalindromeDiscountServiceTest {

    @InjectMocks
    private PalindromeDiscountService palindromeDiscountService;

    private String searchQuery;

    private int id;
    private String brand;
    private String description;
    private String image;
    private int price;

    private List<Product> products;

    @BeforeEach
    public void setUp() {
        Product product = returnNewProduct();
        products = new ArrayList<>();
        products.add(product);
    }

    @Test
    public void shouldReturnFiftyPercentDiscountWhenSearchQueryIsPalindrome() {
        Product product = products.get(0);
        int expectedFinalPrice = (product.getPrice()*50)/100;
        searchQuery="1001";

        palindromeDiscountService.apply(searchQuery,products);

        assertThat(product, hasProperty("finalPrice",is(expectedFinalPrice)));
    }

    @Test
    public void shouldReturnWithoutDiscountWhenSearchQueryIsNotPalindrome() {
        searchQuery="hola";
        Product product = returnNewProduct();

        palindromeDiscountService.apply(searchQuery,products);
        Product productResulted = products.get(0);

        assertEquals(productResulted, product);
    }

    private Product returnNewProduct(){
        id = 1001;
        brand = "A Brand";
        description = "Some Description";
        image = "url/to/image";
        price = 10000;

        return new Product(id,brand,description,image,price);
    }
}