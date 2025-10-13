package com.digenty.app.api.classrooms;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService studentService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0", required = false) int page,
                                            @RequestParam(defaultValue = "15", required = false) int size) {
        return ResponseEntity.ok(studentService.getAllClasses(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getClassById(id));
    }
}
