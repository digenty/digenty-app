package com.digenty.app.api.stock;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdjustQuantityDto {
    @NotNull(message = "quantity cannot be null")
    private Integer quantity;
    @NotNull(message = "reason cannot be null")
    private String reason;
    @NotNull(message = "summary cannot be null")
    private String summary;
}
