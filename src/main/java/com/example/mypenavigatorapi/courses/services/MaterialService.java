package com.example.mypenavigatorapi.courses.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.SaveMaterialDto;
import com.example.mypenavigatorapi.courses.domain.entities.Material;
import com.example.mypenavigatorapi.courses.domain.entities.Module;
import com.example.mypenavigatorapi.courses.domain.repositories.MaterialRepository;
import com.example.mypenavigatorapi.courses.domain.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Material> findAllByModuleId(Long moduleId) {
        return materialRepository.findAllByModuleId(moduleId);
    }

    public Material save(SaveMaterialDto dto, Long moduleId) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module", "id", moduleId));

        Material material = Mapper.map(dto, Material.class);
        material.setModule(module);

        return materialRepository.save(material);
    }

    public Material update(Long id, SaveMaterialDto dto) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material", "id", id));

        material.setName(dto.getName());
        material.setType(dto.getType());
        material.setUrl(dto.getUrl());

        return materialRepository.save(material);
    }

    public ResponseEntity<?> delete(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material", "id", id));

        materialRepository.delete(material);

        return ResponseEntity.ok().build();
    }
}
