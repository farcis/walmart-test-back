package com.walmart.walmarttestback.services;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private IDiscountService discountService;

    private String searchQuery;

    private int id;
    private String brand;
    private String description;
    private String image;
    private int price;

    private Product product;
    private List<Product> expectedProducts;

    @BeforeEach
    public void setUp() {
        id = 1001;
        brand = "A Brand";
        description = "Some Description";
        image = "url/to/image";
        price = 10000;

        product = new Product(id,brand,description,image,price);
        expectedProducts = new ArrayList<>();
        expectedProducts.add(product);
    }

    @Test
    public void shouldCallFindByIdRepoWhenSearchQueryIsANumber(){
        searchQuery = "1111";

        when(productRepository.findById(eq(Integer.parseInt(searchQuery)))).thenReturn(Optional.of(product));

        List<Product> products = productService.getProducts(searchQuery);

        assertThat(products,is(expectedProducts));
    }

    @Test
    public void shouldCallFindByLikeRepoWhenSearchQueryIsStringWithLengthGreaterThanThree(){
        searchQuery = randomAlphanumeric(4);

        when(productRepository.findByDescriptionLikeOrBrandLike(eq(searchQuery),eq(searchQuery))).thenReturn(expectedProducts);

        List<Product> products = productService.getProducts(searchQuery);

        assertThat(products,is(expectedProducts));
    }

    @Test
    public void shouldResponseEmptyProductListWhenSearchQueryIsAStringAlphaNumericWithLightLessThanFour(){
        searchQuery = randomAlphanumeric(3);

        List<Product> products = productService.getProducts(searchQuery);

        assertThat(products.size(),is(0));
    }

    @Test
    public void shouldReturnProductWithDiscountWhenSearchQueryIsAPalindromeString() {
        searchQuery = "abba";

        when(productRepository.findByDescriptionLikeOrBrandLike(eq(searchQuery),eq(searchQuery))).thenReturn(expectedProducts);

        List<Product> products = productService.getProducts(searchQuery);

        assertThat(products,is(expectedProducts));
    }

}