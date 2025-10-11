package com.digenty.app.api.products;

import com.kawa.farm.api.pricing.Currency;

import java.math.BigDecimal;

public record PriceDetail(
        Long id,
        BigDecimal price,
        Long unit,
        String unitName,
        Currency currency
) {
}
