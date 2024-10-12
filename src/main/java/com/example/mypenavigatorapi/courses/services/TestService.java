package com.example.mypenavigatorapi.courses.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.SaveTestDto;
import com.example.mypenavigatorapi.courses.domain.entities.Test;
import com.example.mypenavigatorapi.courses.domain.entities.Module;
import com.example.mypenavigatorapi.courses.domain.repositories.ModuleRepository;
import com.example.mypenavigatorapi.courses.domain.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public Test findById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test", "id", id));
    }

    public Test findByModuleId(Long courseId) {
        return testRepository.findByModuleId(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Test", "courseId", courseId));
    }

    public Test save(SaveTestDto dto, Long moduleId){
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module", "id", moduleId));

        Test test = Mapper.map(dto, Test.class);
        test.setModule(module);

        return testRepository.save(test);
    }

    public Test update(Long id, SaveTestDto dto){
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test", "id", id));

        test.setTitle(dto.getTitle());

        return testRepository.save(test);
    }

    public ResponseEntity<?> delete(Long id){
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test", "id", id));

        testRepository.delete(test);

        return ResponseEntity.ok().build();
    }
}
