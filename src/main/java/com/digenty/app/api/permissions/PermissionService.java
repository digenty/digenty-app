package com.digenty.app.api.permissions;

import com.kawa.farm.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionService {

    private final PermissionRepository permissionRepository;


    public Page<Permission> getPermissions(int pageNo, int pageSize) {
       Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        return permissionRepository.findAll(paging);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
    public List<Permission> getUserPermissions() {
        return permissionRepository.findAll();
    }



    public Permission getOnePermission(long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(()-> new ResourceNotFoundException("No permission with id: " + permissionId));
    }

    public Permission getPermission(long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("No permission with id: " + permissionId));
    }

}
