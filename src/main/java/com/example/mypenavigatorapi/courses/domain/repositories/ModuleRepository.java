package com.example.mypenavigatorapi.courses.domain.repositories;

import com.example.mypenavigatorapi.courses.domain.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findAllByCourseId(Long courseId);
}
