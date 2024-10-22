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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

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

    public List<UserModuleTracking> startCourseTracking(Long userId, Long courseId){
        User user = userService.findById(userId);
        Course course = courseService.findById(courseId);
        List<Module> modules = moduleService.findAllByCourseId(courseId);

        List<UserModuleTracking> trackings = new ArrayList<>();

        AtomicBoolean isFirst = new AtomicBoolean(true);
        modules.forEach(module -> {
            UserModuleTracking tracking = new UserModuleTracking();
            tracking.setUser(user);
            tracking.setCourse(course);
            tracking.setModule(module);
            tracking.setIsStarted(false);
            tracking.setIsCompleted(false);
            tracking.setIsVideoCompleted(false);
            tracking.setIsMaterialDownloaded(false);
            tracking.setTestScore(0);
            tracking.setProgress(0);



            if (isFirst.get()) {
                tracking.setIsBlocked(false);
                isFirst.set(false);
            } else {
                tracking.setIsBlocked(true);
            }

            trackings.add(tracking);
        });
        return userModuleTrackingRepository.saveAll(trackings);
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
        userModuleTracking.setProgress(100);

        List<UserModuleTracking> userTracking = findAllByUserIdAndCourseId(userModuleTracking.getUser().getId(), userModuleTracking.getCourse().getId());
        AtomicReference<UserModuleTracking> next = new AtomicReference<>();

        AtomicBoolean isNext = new AtomicBoolean(false);
        userTracking.forEach(tracking -> {
            if (isNext.get()) {
                next.set(tracking);
                tracking.setIsBlocked(false);
                isNext.set(false);
            }
            if (tracking.getId().equals(id)) {
                isNext.set(true);
            }
        });

        if (next.get() != null) {
            userModuleTrackingRepository.save(next.get());
        }

        return userModuleTrackingRepository.save(userModuleTracking);
    }

    public ResponseEntity<?> delete(Long id){
        UserModuleTracking userModuleTracking = findById(id);
        userModuleTrackingRepository.delete(userModuleTracking);
        return ResponseEntity.ok().build();
    }
}
