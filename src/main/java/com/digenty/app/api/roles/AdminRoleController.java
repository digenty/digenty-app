package com.digenty.app.api.roles;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/roles")
public class AdminRoleController {

    @Autowired
    private  RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody @Valid RoleDto roleDto){
        return new ResponseEntity<>(roleService.adminCreateRole(roleDto),HttpStatus.CREATED);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<?> getRole(@PathVariable long roleId){
        return new ResponseEntity<>(roleService.getOneRole(roleId),HttpStatus.OK);
    }

    @PatchMapping("/{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable Long roleId, @RequestBody RoleDto roleDto){
        return new ResponseEntity<>(roleService.updateRole(roleId, roleDto),HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Object> delete(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok(Map.of("message", "role deleted"));
    }
}
