package com.digenty.app.api.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findAllByRoleId(Long roleId);
    @Transactional
    void deleteAllByRoleIdAndPermissionIdNotIn(Long roleId, List<Long> permissions);

    Optional<RolePermission> findByRoleIdAndPermissionId(Long roleId, Long permissionName);

    Set<RolePermission> findAllByRoleIdIn(List<Long> roleIds);
}
