package com.walmart.walmarttestback.services;

import com.walmart.walmarttestback.models.Product;

import java.util.List;

public class PalindromeDiscountService implements IDiscountService {
    @Override
    public void apply(String searchQuery, List<Product> products) {
        final float DiscountPercentage  = 50;

        if (isPalindrome(searchQuery)){
            products.forEach(item -> {
                item.setDiscount(DiscountPercentage);
                item.setFinalPrice((int) ((item.getPrice() * DiscountPercentage) / 100));
            });

        }
    }

    private boolean isPalindrome(String str) {
        int n = str.length();
        for (int i = 0; i < n/2; ++i) {
            if (str.charAt(i) != str.charAt(n-i-1)){
                return false;
            }
        }
        return true;
    }
}
