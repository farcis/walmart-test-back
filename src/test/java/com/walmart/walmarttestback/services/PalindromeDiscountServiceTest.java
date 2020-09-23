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
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PalindromeDiscountServiceTest {

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

        List<Product> discountProducts = palindromeDiscountService.getProductsWithDiscount(searchQuery,products);

        assertThat(discountProducts.get(0), hasProperty("finalPrice",is(expectedFinalPrice)));
        assertThat(discountProducts.get(0).getDiscount(), is((float)50));
        assertThat(discountProducts.get(0).getFinalPrice(), is(5000));
    }

    @Test
    public void shouldReturnWithoutDiscountWhenSearchQueryIsNotPalindrome() {
        searchQuery="hola";
        Product product = returnNewProduct();

        List<Product> discountProducts = palindromeDiscountService.getProductsWithDiscount(searchQuery,products);

        assertEquals(discountProducts.get(0), product);
    }

    @Test
    public void shouldReturnWithoutDiscountWhenSearchQueryIsPalindromeLenghtLessThanThree() {
        searchQuery="11";
        Product product = returnNewProduct();

        List<Product> discountProducts = palindromeDiscountService.getProductsWithDiscount(searchQuery,products);

        assertEquals(discountProducts.get(0), product);
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