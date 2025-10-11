package com.digenty.app.api.roles;

import com.digenty.app.commons.core.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role extends BaseEntity {
    private String name;
    private String alias;
}
