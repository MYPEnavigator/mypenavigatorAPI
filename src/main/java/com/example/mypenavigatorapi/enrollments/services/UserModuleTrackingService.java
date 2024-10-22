package com.example.mypenavigatorapi.enrollments.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.entities.Course;
import com.example.mypenavigatorapi.courses.domain.entities.Module;
import com.example.mypenavigatorapi.courses.services.CourseService;
import com.example.mypenavigatorapi.courses.services.ModuleService;
import com.example.mypenavigatorapi.enrollments.domain.dto.SaveUserModuleTrackingDto;
import com.example.mypenavigatorapi.enrollments.domain.entities.UserModuleTracking;
import com.example.mypenavigatorapi.enrollments.domain.repositories.UserModuleTrackingRepository;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserModuleTrackingService {
    @Autowired
    private UserModuleTrackingRepository userModuleTrackingRepository;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    public List<UserModuleTracking> findAllByUserIdAndCourseId(Long userId, Long courseId){
        return userModuleTrackingRepository.findAllByUserIdAndCourseId(userId, courseId);
    }

    public UserModuleTracking findById(Long id) {
        return userModuleTrackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserModuleTracking", "id", id));
    }

    public UserModuleTracking save(SaveUserModuleTrackingDto dto, Long userId, Long courseId, Long moduleId){
        User user = userService.findById(userId);
        Course course = courseService.findById(courseId);
        Module module = moduleService.findById(moduleId);

        UserModuleTracking userModuleTracking = Mapper.map(dto, UserModuleTracking.class);
        userModuleTracking.setIsBlocked(true);
        userModuleTracking.setUser(user);
        userModuleTracking.setCourse(course);
        userModuleTracking.setModule(module);

        return userModuleTrackingRepository.save(userModuleTracking);
    }

    public UserModuleTracking update(Long id, SaveUserModuleTrackingDto dto){
        UserModuleTracking userModuleTracking = findById(id);
        Mapper.merge(dto, userModuleTracking);
        return userModuleTrackingRepository.save(userModuleTracking);
    }

    public UserModuleTracking startModule(Long id){
        UserModuleTracking userModuleTracking = findById(id);

        userModuleTracking.setIsStarted(true);
        userModuleTracking.setIsBlocked(false);
        userModuleTracking.setStartedAt(new Date());

        return userModuleTrackingRepository.save(userModuleTracking);
    }

    public UserModuleTracking completeModule(Long id){
        UserModuleTracking userModuleTracking = findById(id);

        userModuleTracking.setIsCompleted(true);
        userModuleTracking.setCompletedAt(new Date());

        return userModuleTrackingRepository.save(userModuleTracking);
    }

    public ResponseEntity<?> delete(Long id){
        UserModuleTracking userModuleTracking = findById(id);
        userModuleTrackingRepository.delete(userModuleTracking);
        return ResponseEntity.ok().build();
    }
}
