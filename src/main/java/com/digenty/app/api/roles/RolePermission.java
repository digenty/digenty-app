package com.digenty.app.api.roles;

import com.digenty.app.commons.core.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "role_permissions")
public class RolePermission extends BaseEntity {
    private Long roleId;
    private Long permissionId;
}
