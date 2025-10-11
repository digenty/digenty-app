package com.digenty.app.api.pricing;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePriceDto(
        @NotNull(message = "productId must not be null")
        Long productId,
        @NotNull(message = "price must not be null")
        BigDecimal price,
        @NotNull(message = "currency must not be null")
        Currency currency,
        @NotNull(message = "unitId must not be null")
        Long unitId
) {
}
