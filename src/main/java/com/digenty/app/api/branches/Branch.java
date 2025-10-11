package com.digenty.app.api.branches;

import com.digenty.app.commons.core.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Entity
@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Branch extends BaseEntity {
    private Long schoolId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String motto;
}
