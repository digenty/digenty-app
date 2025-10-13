package com.digenty.app.api.arms;

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
public class Arm extends BaseEntity {
    private Long classId;
    private String name;
}
