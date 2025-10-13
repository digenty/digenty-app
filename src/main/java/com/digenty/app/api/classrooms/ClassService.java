package com.digenty.app.api.classrooms;

import com.digenty.app.api.pricing.PriceService;
import com.digenty.app.exceptions.ResourceNotFoundException;
import com.digenty.app.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassService {
    private final ClassRepository studentRepository;
    private final PriceService priceService;

    public Page<ClassRoom> getAllClasses(int page, int size) {
        return studentRepository.findAll(PageUtil.getPageable(page, size));
    }

    public ClassRoom getClassById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Class id "+ id + " not found"));
    }
}
