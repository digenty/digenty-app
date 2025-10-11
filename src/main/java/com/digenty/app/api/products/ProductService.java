package com.digenty.app.api.products;

import com.digenty.app.api.pricing.PriceService;
import com.digenty.app.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final PriceService priceService;

    public Product createProduct(CreateProductDto createProductDto) {
        log.info("create product");
        log.info("security contest {}", SecurityContextHolder.getContext().getAuthentication().getName());
        return productRepository.save(createProductDto.toProduct());
    }

    public Page<ProductListResponse> getAllProducts(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable).map(this::createProductResponse);
    }

    public ProductListResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        return createProductResponse(product);
    }

    private ProductListResponse createProductResponse(Product product) {
        return new ProductListResponse(
                product.getName(), product.getType(), product.getDescription(),
                product.getCategory(), product.getImage(), product.getStatus(),
                priceService.getPrices(product.getId())
        );
    }


}
