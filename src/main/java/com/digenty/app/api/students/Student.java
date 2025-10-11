package com.digenty.app.api.students;

import com.digenty.app.commons.core.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String admissionNumber;
    private String image;
    private BoardingStatus boardingStatus;
    private Date dateOfBirth;
    private String address;
    private String stateOfOrigin;
    private String nationality;
    private String emergencyContact;
    private String phoneNumber;
    private Long classId;
}
