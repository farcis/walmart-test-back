package com.walmart.walmarttestback.services.strategies;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchByAllFieldsStrategyTest {
    @InjectMocks
    SearchByAllFieldsStrategy searchByAllFieldsStrategy;

    @Mock
    ProductRepository productRepository;

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
    public void shouldCallProductRepository() {
        String searchQuery="12";

        List<Product> products = searchByAllFieldsStrategy.search(searchQuery);

        verify(productRepository).findByDescriptionLikeOrBrandLike(searchQuery,searchQuery);
    }

    @Test
    public void shouldCallProductRepositoryAndReturnAListOfProduct() {
        String searchQuery="12234";
        Product product = returnNewProduct();
        List<Product> expectedProducts = new ArrayList<>(Arrays.asList(product));

        when(productRepository.findByDescriptionLikeOrBrandLike(searchQuery,searchQuery)).thenReturn(Optional.of(product));

        List<Product> products = searchByAllFieldsStrategy.search(searchQuery);

        assertThat(products.get(0),is(expectedProducts.get(0)));
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