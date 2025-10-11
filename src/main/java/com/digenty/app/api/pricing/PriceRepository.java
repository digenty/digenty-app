package com.digenty.app.api.pricing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByProductId(Long productId);
    Optional<Price> findByProductIdAndUnitId(Long productId,Long unitId);
}
