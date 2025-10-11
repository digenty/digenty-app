package com.digenty.app.api.branches;

import com.digenty.app.api.pricing.PriceService;
import com.digenty.app.api.products.CreateProductDto;
import com.digenty.app.api.products.ProductListResponse;
import com.digenty.app.api.students.Student;
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
public class BranchService {
    private final BranchRepository studentRepository;
    private final PriceService priceService;

    public Student createProduct(CreateProductDto createProductDto) {
        log.info("create product");
        log.info("security contest {}", SecurityContextHolder.getContext().getAuthentication().getName());
        return studentRepository.save(createProductDto.toProduct());
    }

    public Page<ProductListResponse> getAllProducts(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return studentRepository.findAll(pageable).map(this::createProductResponse);
    }

    public ProductListResponse getProductById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        return createProductResponse(student);
    }

    private ProductListResponse createProductResponse(Student student) {
        return new ProductListResponse(
                student.getName(), student.getType(), student.getDescription(),
                student.getCategory(), student.getImage(), student.getStatus(),
                priceService.getPrices(student.getId())
        );
    }


}
