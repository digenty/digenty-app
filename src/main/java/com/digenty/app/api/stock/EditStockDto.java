package com.digenty.app.api.stock;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EditStockDto {

    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "description cannot be null")
    private String description;
    @NotNull(message = "category cannot be null")
    private String category;
    @NotNull(message = "image_path cannot be null")
    private String imagePath;
    @NotNull(message = "unit cannot be null")
    private String unit;
    @NotNull(message = "quantity cannot be null")
    private Integer quantity;
    @NotNull(message = "price cannot be null")
    private BigDecimal price;
    @NotNull(message = "cost_price cannot be null")
    private BigDecimal costPrice;
}
