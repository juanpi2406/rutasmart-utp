package com.rutasmart.api.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    Optional<StudentProfile> findByEmailIgnoreCase(String email);
    Optional<StudentProfile> findByCodeIgnoreCase(String code);
}
