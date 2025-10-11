package com.digenty.app.api.permissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {

    @Query("select u.permission from UserPermission u where u.user.id = ?1")
    List<Permission> findPermissionByUser(Long userId);

//    @Query("SELECT new com.termii.user.users.dtos.UserPermissionDTO(" +
//            "u.user.id, " +
//            "CONCAT(u.user.firstName, ' ', u.user.lastName), " +
//            "CASE " +
//            "WHEN u.permission.name = 'use_inbox' THEN 'Agent' " +
//            "WHEN u.permission.name = 'manage_inbox' THEN 'Admin' " +
//            "ELSE u.permission.name " +
//            "END, " +
//            "u.user.email, " +
//            "u.createdAt) " +  // Added createdAt to selection
//            "FROM UserPermission u " +
//            "WHERE u.permission.name IN ?1 " +
//            "AND u.applicationId = ?2 " +
//            "ORDER BY u.createdAt DESC")  // Changed sorting to use createdAt
//    Page<UserPermissionDTO> findAllPermissionsWithPrioritizedOrder(
//            List<String> permissions,
//            Long applicationId,
//            Pageable pageable
//    );

//    @Query("SELECT new com.termii.user.users.dtos.UserPermissionDTO(" +
//            "u.user.id, " +
//            "CONCAT(u.user.firstName, ' ', u.user.lastName), " +
//            "CASE " +
//            "WHEN u.permission.name = 'use_inbox' THEN 'Agent' " +
//            "WHEN u.permission.name = 'manage_inbox' THEN 'Admin' " +
//            "ELSE u.permission.name " +
//            "END, " +
//            "u.user.email) " +
//            "FROM UserPermission u " +
//            "WHERE u.permission.name IN ?1 " +
//            "AND u.applicationId = ?2 " +
//            "ORDER BY u.user.email, " +
//            "CASE " +
//            "WHEN u.permission.name = 'manage_inbox' THEN 1 " +
//            "WHEN u.permission.name = 'use_inbox' THEN 2 " +
//            "ELSE 3 " +
//            "END ASC")
//    Page<UserPermissionDTO> findAllPermissionsWithPrioritizedOrder(
//            List<String> permissions,
//            Long applicationId, Pageable pageable
//    );
//
//    Boolean existsByUserAndPermission_Name(User user, String permissionName);
//
//    void deleteByUserAndPermission_Name(User user, String permissionName);
//
//
//    @Query("SELECT up FROM UserPermission up WHERE up.user.email = :email")
//    Optional<UserPermission> findByEmail(String email);
//
//    @Query("SELECT up FROM UserPermission up WHERE up.user.email = :email")
//    List<UserPermission> findAllByEmail(@Param("email") String email);

}
