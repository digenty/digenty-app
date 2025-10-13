package com.digenty.app.api.branches;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0", required = false) int page,
                                            @RequestParam(defaultValue = "15", required = false) int size) {
        return ResponseEntity.ok(branchService.getAllBranches(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }
}
