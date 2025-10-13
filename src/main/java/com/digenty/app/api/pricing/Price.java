package com.digenty.app.api.pricing;

import com.digenty.app.commons.core.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"productId", "unitId"})})
public class Price extends BaseEntity {
    private BigDecimal price;
    private Long productId;
    private Currency currency;
    private Long unitId;

    public Price updatePricing(UpdatePriceDto updatePriceDto) {
        if(Objects.nonNull(updatePriceDto.currency())){
            this.currency=updatePriceDto.currency();
        }
        if(Objects.nonNull(updatePriceDto.price())){
            this.price=updatePriceDto.price();
        }
        return this;
    }
}
