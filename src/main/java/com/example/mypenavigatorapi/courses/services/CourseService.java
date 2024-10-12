package com.example.mypenavigatorapi.courses.services;

import com.example.mypenavigatorapi.common.exceptions.BadRequestException;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.SaveCourseDto;
import com.example.mypenavigatorapi.courses.domain.entities.Course;
import com.example.mypenavigatorapi.courses.domain.repositories.CourseRepository;
import com.example.mypenavigatorapi.users.domain.entities.Bank;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.BankService;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserService userService;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
    }

    public Course save(SaveCourseDto dto, Long userId){
        User user = userService.findById(userId);

        Bank bank = user.getBank();

        if(bank == null) {
            throw new BadRequestException("You must belong to a bank to create a course");
        }

        Course course = Mapper.map(dto, Course.class);
        course.setBank(bank);
        course.setUser(user);

        return courseRepository.save(course);
    }

    public Course update(Long id, SaveCourseDto dto){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setRewardPoints(dto.getRewardPoints());

        return courseRepository.save(course);
    }

    public ResponseEntity<?> delete (Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        courseRepository.delete(course);

        return ResponseEntity.ok().build();
    }
}
