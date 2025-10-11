package com.digenty.app.api.permissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    List<Permission> findAllByNameIn(List<String> names);

//    Optional<Permission> findDistinctByName(String name);

    Set<Permission> findAllByIdIn(List<Long> ids);

    Optional<Permission> findDistinctByName(String name);
}
