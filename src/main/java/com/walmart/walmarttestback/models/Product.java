package com.walmart.walmarttestback.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product{

    @Field("id")
    private int id;
    private String brand;
    private String description;

    private String image;
    private int price;

    private float discount;
    private int finalPrice;

    public Product(int id, String brand, String description, String image, int price) {
        this.id=id;
        this.brand=brand;
        this.description=description;
        this.image=image;
        this.price=price;
    }


}
