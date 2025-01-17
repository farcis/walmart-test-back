package com.walmart.walmarttestback.services;

import com.walmart.walmarttestback.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalindromeDiscountService implements IDiscountService {

    @Autowired
    public PalindromeDiscountService(){}

    @Override
    public List<Product> getProductsWithDiscount(String searchQuery, List<Product> products) {
        final float DiscountPercentage  = 50;

        if (isPalindrome(searchQuery)){
            products.forEach(item -> {
                item.setDiscount(DiscountPercentage);
                item.setFinalPrice((int) ((item.getPrice() * DiscountPercentage) / 100));
            });
        }

        return products;
    }

    private boolean isPalindrome(String str) {
        if(str.length() < 3) return false;

        int n = str.length();
        for (int i = 0; i < n/2; ++i) {
            if (str.charAt(i) != str.charAt(n-i-1)){
                return false;
            }
        }
        return true;
    }
}
