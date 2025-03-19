package com.example.mypenavigatorapi.courses.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.SaveModuleDto;
import com.example.mypenavigatorapi.courses.domain.entities.Course;
import com.example.mypenavigatorapi.courses.domain.entities.Module;
import com.example.mypenavigatorapi.courses.domain.repositories.CourseRepository;
import com.example.mypenavigatorapi.courses.domain.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Module> findAllByCourseId(Long courseId) {
        return moduleRepository.findAllByCourseId(courseId);
    }

    public Module findById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module", "id", id));
    }

    public Module save(SaveModuleDto dto, Long courseId){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));

        List<Module> modules = moduleRepository.findAllByCourseId(courseId);

        Module module = Mapper.map(dto, Module.class);
        module.setCourse(course);
        module.setOrder(modules.size() + 1);

        return moduleRepository.save(module);
    }

    public Module update(Long id, SaveModuleDto dto){
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module", "id", id));

        Mapper.merge(dto, module);

        return moduleRepository.save(module);
    }

    public Module updateActiveStatus(Long id, boolean active){
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module", "id", id));

        module.setActive(active);

        return moduleRepository.save(module);
    }

    public ResponseEntity<?> delete(Long id){
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module", "id", id));

        moduleRepository.delete(module);

        return ResponseEntity.ok().build();
    }
}
