package com.digenty.app.api.schools;

import com.digenty.app.api.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<Student, Long> {
}
