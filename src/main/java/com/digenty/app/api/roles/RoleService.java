package com.digenty.app.api.roles;

import com.digenty.app.api.permissions.Permission;
import com.digenty.app.api.permissions.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    private final PermissionRepository permissionRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public List<RolePermResDto> getAllRoles() {
        List<RolePermission> roleAuthorities = rolePermissionRepository.findAll();

        List<Role> roles = roleRepository.findAll();
        List<Permission> permissions = permissionRepository.findAll();
        Map<Long,Permission> permissionMap = permissions.stream().collect(Collectors.toMap(Permission::getId, permission -> permission));
        return roles.stream().map(e-> new RolePermResDto(e.getAlias(), roleAuthorities.stream()
                .filter(r-> e .getId().equals(r.getRoleId()))
                .map(j->permissionMap.get(j.getPermissionId())).collect(Collectors.toList()))).toList();
    }

    public Page<Role> getPaginatedRoles(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        return roleRepository.findAll(pageable);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role adminCreateRole(RoleDto roleDto) {
        Optional<Role> existingModule = roleRepository.findDistinctByNameEqualsIgnoreCase(roleDto.getName());

        if(existingModule.isPresent()) throw new IllegalArgumentException("Role name '"+ roleDto.getName() + "' already exists");

        Role role = Role.builder()
                .name(roleDto.getName())
                .alias(WordUtils.capitalizeFully(String.join(" ", roleDto.getName().split("_"))))
                .build();

        return roleRepository.save(role);
    }

    public Role getRole(long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Found no role with id : " + roleId));
    }

    public Role getOneRole(long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Found no role with id : " + roleId));
    }

    @Transactional
    public Role updateRole(Long roleId, RoleDto roleDto) {
        Role existingRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Found no role with id : " + roleId));

        existingRole.setName(roleDto.getName());
        existingRole.setAlias(WordUtils.capitalizeFully(String.join(" ", roleDto.getName().split("_"))));

        Role savedRole = roleRepository.save(existingRole);

        Set<Permission> permissions = permissionRepository.findAllByIdIn(roleDto.getPermissionIds());
        if (permissions.size() != roleDto.getPermissionIds().size()) {
            throw new RuntimeException("One or more regular user permission IDs not found");
        }

        addPermissions(savedRole, permissions);
        return savedRole;
    }

    @Transactional
    public void deleteRole(Long roleId){
        Role existingRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("No role with id: " + roleId));

        List<RolePermission> roleAuthorities = rolePermissionRepository.findAllByRoleId(roleId);
        rolePermissionRepository.deleteAll(roleAuthorities);

        roleRepository.delete(existingRole);
    }

    @Secured("ROLE_EDIT_ROLE")
    public Role create(Role role) {
        Role saved = createRole(role);
//        addPermissions(saved, role.getPermissions());
        return saved;
    }

    private void addPermissions(Role saved, Set<Permission> permissions) {
        if (!ObjectUtils.isEmpty(permissions)) {
            int count = 0;
            List<Long> collect = permissions.stream().map(Permission::getId).collect(toList());
            rolePermissionRepository.deleteAllByRoleIdAndPermissionIdNotIn(saved.getId(),collect);
            for (Permission p : permissions) {
                try {
                    RolePermission roleAuthority = new RolePermission(saved.getId(), p.getId());
                    if (rolePermissionRepository.findByRoleIdAndPermissionId(saved.getId(), p.getId()).isEmpty()) {
                        rolePermissionRepository.save(roleAuthority);
                    }
                    count++;
                } catch (Exception e) {

                    log.warn(e.getMessage(), e);
                }
            }
            if (count > 0) {
                //SUCCESS
            }
        }
    }

    public Set<Permission> assignedAuthorities(List<Role> roles) {
        List<Long> rolesIds = roles.stream().map(Role::getId).toList();
        return permissionRepository.findAllByIdIn(rolePermissionRepository.findAllByRoleIdIn(rolesIds).stream().map(RolePermission::getPermissionId).collect(toList()));
    }
}
