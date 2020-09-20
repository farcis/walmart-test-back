package com.walmart.walmarttestback.services;

import com.walmart.walmarttestback.models.Product;
import com.walmart.walmarttestback.services.factories.SearchFactory;
import com.walmart.walmarttestback.services.strategies.ISearchStrategy;
import com.walmart.walmarttestback.services.strategies.SearchByAllFieldsStrategy;
import com.walmart.walmarttestback.services.strategies.SearchByIdStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private IDiscountService discountService;

    @Mock
    private ISearchStrategy searchStrategy;

    @Mock
    private SearchByAllFieldsStrategy searchByAllFieldsStrategy;

    @Mock
    private SearchByIdStrategy searchByIdStrategy;

    @Mock
    private SearchFactory searchFactory;

    private int id;
    private String brand;
    private String description;
    private String image;
    private int price;

    private Product product;
    private List<Product> expectedProducts;
    private String searchQuery;

    @BeforeEach
    public void setUp() {
        id = 1000;
        brand = "A Brand";
        description = "Some Description abba";
        image = "url/to/image";
        price = 10000;

        searchQuery="";

        product = new Product(id,brand,description,image,price);
        expectedProducts = new ArrayList<>();
        expectedProducts.add(product);
    }

    @Test
    public void shouldReturnAListOfProductsWhenSearchQueryIsAlphaNumericGreaterThanThree(){
        searchQuery="A Brand";

        when(searchFactory.getSearchStrategy(eq(searchQuery))).thenReturn(searchByAllFieldsStrategy);
        when(searchByAllFieldsStrategy.search(eq(searchQuery))).thenReturn(expectedProducts);
        when(discountService.getProductsWithDiscount(eq(searchQuery),eq(expectedProducts))).thenReturn(expectedProducts);

        List<Product> products = productService.getProducts(searchQuery);

        assertTrue(products.containsAll(expectedProducts));
    }

    @Test
    public void shouldReturnAListOfProductsWithDiscountWhenSearchQueryPalindrome(){
        searchQuery="abba";
        int finalPrice = (product.getPrice()*50)/100;
        float discount = 50;
        Product productDiscount = new Product(id,brand,description,image,price,discount,finalPrice);
        List<Product> discountExpectedProducts = new ArrayList<>(Arrays.asList(productDiscount));

        when(searchFactory.getSearchStrategy(eq(searchQuery))).thenReturn(searchByAllFieldsStrategy);
        when(searchByAllFieldsStrategy.search(eq(searchQuery))).thenReturn(expectedProducts);
        when(discountService.getProductsWithDiscount(eq(searchQuery),eq(expectedProducts))).thenReturn(discountExpectedProducts);

        List<Product> products = productService.getProducts(searchQuery);
        Product actualProduct = products.get(0);

        assertThat(discountExpectedProducts.get(0).getFinalPrice(), is(finalPrice));
        assertThat(discountExpectedProducts.get(0).getDiscount(), is(discount));
    }

    @Test
    public void shouldReturnAListOfProductsWhenSearchQueryIsNumeric(){
        searchQuery="1000";

        when(searchFactory.getSearchStrategy(eq(searchQuery))).thenReturn(searchByIdStrategy);
        when(searchByIdStrategy.search(eq(searchQuery))).thenReturn(expectedProducts);
        when(discountService.getProductsWithDiscount(eq(searchQuery),eq(expectedProducts))).thenReturn(expectedProducts);

        List<Product> products = productService.getProducts(searchQuery);

        assertTrue(products.containsAll(expectedProducts));
    }

    @Test
    public void ShouldReturnIllegalExceptionWhenSearchQueryIsEmpty() {
        String nullSearchQuery = "";

        assertThrows(IllegalArgumentException.class, () -> productService.getProducts(nullSearchQuery));
    }

}