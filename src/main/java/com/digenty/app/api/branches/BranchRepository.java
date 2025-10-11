package com.digenty.app.api.branches;

import com.digenty.app.api.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Student, Long> {
}
