package com.ZooFood.Diner_David_ZooFood_CaseStudy.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ProductDTO {
    Long id;
    String name;
    int categoryId;
    double price;
    double weight;
    String description;
    String imageName;

}
