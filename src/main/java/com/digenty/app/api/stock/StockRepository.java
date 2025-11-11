package com.digenty.app.api.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Optional<Stock> findByName(String name);
    Optional<Stock> findByCategory(String category);
    Optional<Stock> findByStatus(String status);

}
