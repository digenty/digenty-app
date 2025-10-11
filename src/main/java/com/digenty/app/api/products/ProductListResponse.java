package com.digenty.app.api.products;

import java.util.List;

public record ProductListResponse(
        String name,
        String type,
        String description,
        String category,
        String image,
        ProductStatus status,
        List<PriceDetail> price
) {
}
