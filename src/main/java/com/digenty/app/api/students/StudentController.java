package com.digenty.app.api.students;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0", required = false) int page,
                                            @RequestParam(defaultValue = "15", required = false) int size) {
        return ResponseEntity.ok(studentService.getAllStudents(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }
}
