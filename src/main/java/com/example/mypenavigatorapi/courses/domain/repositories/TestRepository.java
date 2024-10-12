package com.example.mypenavigatorapi.courses.domain.repositories;

import com.example.mypenavigatorapi.courses.domain.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
    Optional<Test> findByModuleId(Long moduleId);
}
