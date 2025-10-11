package com.digenty.app.api.classrooms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<ClassRoom, Long> {
}
