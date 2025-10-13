package com.digenty.app.api.pricing;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/pricing")
public class AdminPricingController {
    private final PriceService priceService;
//    @PostMapping
//    public ResponseEntity<?> createPricing(@RequestBody @Valid CreatePriceDto createPriceDto) {
//        return new ResponseEntity<>(priceService.createPricing(createPriceDto), HttpStatus.CREATED);
//    }
//    @GetMapping("/{productId}")
//    public ResponseEntity<?> getPricing(@PathVariable Long productId) {
//        return ResponseEntity.ok(priceService.getPrices(productId));
//    }
//
//    @PutMapping
//    public ResponseEntity<?> updatePricing(@RequestBody @Valid UpdatePriceDto createPriceDto) {
//        return new ResponseEntity<>(priceService.updatePricing(createPriceDto), HttpStatus.OK);
//    }
//    @DeleteMapping("/{priceId}")
//    public ResponseEntity<?> deletePricing(@PathVariable Long priceId) {
//        return new ResponseEntity<>(priceService.deletePrice(priceId), HttpStatus.OK);
//    }
}
