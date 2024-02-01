package com.igorbarreto.anotaaichallenge.domain.product;

import com.igorbarreto.anotaaichallenge.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String title;

    private String description;

    private String ownerId;

    private Integer price;

    private String category;

    public Product(ProductDTO productData) {

        this.title = productData.title();
        this.description = productData.description();
        this.ownerId = productData.ownerId();
        this.price = productData.price();
        this.category = productData.categoryId();

    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();

        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerId", this.ownerId);
        json.put("price", this.price);
        json.put("category", this.category);
        json.put("id", this.id);
        json.put("type", "product");

        return json.toString();
    }

}
