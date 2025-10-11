package com.digenty.app.api.schools;

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
public class School extends BaseEntity {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String motto;
}
