package com.example.mypenavigatorapi.courses.domain.repositories;

import com.example.mypenavigatorapi.courses.domain.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findAllByModuleId(Long moduleId);
}
