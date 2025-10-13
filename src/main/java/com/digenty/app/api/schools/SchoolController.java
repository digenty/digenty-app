package com.digenty.app.api.schools;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService studentService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0", required = false) int page,
                                            @RequestParam(defaultValue = "15", required = false) int size) {
        return ResponseEntity.ok(studentService.getAllSchool(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getSchoolById(id));
    }
}
