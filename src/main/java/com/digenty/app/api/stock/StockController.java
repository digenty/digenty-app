package com.digenty.app.api.stock;

import com.digenty.app.api.roles.RoleDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping("stocks")
    public ResponseEntity<?> getAllStocks(@RequestParam(defaultValue = "0", required = false) int page,
                                            @RequestParam(defaultValue = "15", required = false) int size) {
        return ResponseEntity.ok(stockService.getAllStocks(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getStockByName(@PathVariable String name) {
        return ResponseEntity.ok(stockService.getStockByName(name));
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> getStockByCategory(@PathVariable String category) {
        return ResponseEntity.ok(stockService.getStockByCategory(category));
    }

    @GetMapping("/{status}")
    public ResponseEntity<?> getStockByStatus(@PathVariable String status) {
        return ResponseEntity.ok(stockService.getStockByStatus(status));
    }

    @PostMapping
    public ResponseEntity<?> createStock(@RequestBody @Valid CreateStockDto createStockDto) {
        return new ResponseEntity<>(stockService.createStock(createStockDto), HttpStatus.CREATED);
    }

    @PutMapping("/{stockId}")
    public ResponseEntity<?> editStock(@RequestBody @Valid EditStockDto editStockDto, @PathVariable long stockId) {
        return new ResponseEntity<>(stockService.editStock(editStockDto, stockId),HttpStatus.CREATED);
    }

    @PutMapping("/{stockId}")
    public ResponseEntity<?> adjustStockQuantity(@RequestBody @Valid AdjustQuantityDto adjustQuantityDto, @PathVariable long stockId) {
        return new ResponseEntity<>(stockService.adjustQuantity(adjustQuantityDto, stockId) ,HttpStatus.CREATED);
    }

    @DeleteMapping("/{stockId}")
    public ResponseEntity<?> deleteStock(@PathVariable long stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.ok(Map.of("message", "stock deleted"));
    }




}
