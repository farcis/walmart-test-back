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
}
