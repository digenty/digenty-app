package com.digenty.app.api.branches;

import com.digenty.app.api.pricing.PriceService;
import com.digenty.app.api.students.Student;
import com.digenty.app.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchService {
    private final BranchRepository studentRepository;
    private final PriceService priceService;

    public Page<Student> getAllBranches(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return studentRepository.findAll(pageable);
    }

    public Object getBranchById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Branch not found"));
    }
}
