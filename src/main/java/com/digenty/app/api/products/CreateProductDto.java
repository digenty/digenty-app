package com.digenty.app.api.products;

import java.math.BigDecimal;
import java.util.List;

public record CreateProductDto(
         String name,
         String type,
         String description,
         String category,
         String image,
         ProductStatus status,
         List<PriceDetail> priceDetails
) {
    public Product toProduct() {
        return new Product(name,type, description, category, image, status);
    }
}
