package com.digenty.app.api.roles;


import com.digenty.app.api.permissions.Permission;

import java.util.List;

public record RolePermResDto(String role, List<Permission> permissions) {
}
