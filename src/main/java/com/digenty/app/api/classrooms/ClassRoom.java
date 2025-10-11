package com.digenty.app.api.classrooms;

import com.digenty.app.api.students.BoardingStatus;
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
public class ClassRoom extends BaseEntity {
    private String firstName;
    private String lastName;
    private String type;
    private String description;
    private String category;
    private String image;
    private BoardingStatus status;
}
