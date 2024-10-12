package com.example.mypenavigatorapi.enrollments.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.entities.Course;
import com.example.mypenavigatorapi.courses.services.CourseService;
import com.example.mypenavigatorapi.enrollments.domain.dto.SaveCourseCompletionHistoryDto;
import com.example.mypenavigatorapi.enrollments.domain.entities.CourseCompletionHistory;
import com.example.mypenavigatorapi.enrollments.domain.repositories.CourseCompletionHistoryRepository;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseCompletionHistoryService {
    @Autowired
    private CourseCompletionHistoryRepository courseCompletionHistoryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    public List<CourseCompletionHistory> findAllByUserId(Long userId){
        return courseCompletionHistoryRepository.findAllByUserId(userId);
    }

    public List<CourseCompletionHistory> findAllByCourseId(Long courseId){
        return courseCompletionHistoryRepository.findAllByCourseId(courseId);
    }


    public CourseCompletionHistory findById(Long id) {
        return courseCompletionHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CourseCompletionHistory", "id", id));
    }

    public CourseCompletionHistory save(SaveCourseCompletionHistoryDto dto){
        User user = userService.findById(dto.getUserId());
        Course course = courseService.findById(dto.getCourseId());

        CourseCompletionHistory courseCompletionHistory = Mapper.map(dto, CourseCompletionHistory.class);

        courseCompletionHistory.setUser(user);
        courseCompletionHistory.setCourse(course);
        courseCompletionHistory.setPointsEarned(course.getRewardPoints());
        courseCompletionHistory.setBank(course.getBank());
        courseCompletionHistory.setCompletionDate(new Date());

        return courseCompletionHistoryRepository.save(courseCompletionHistory);
    }

    public ResponseEntity<?> delete(Long id){
       CourseCompletionHistory courseCompletionHistory = courseCompletionHistoryRepository
               .findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("CourseCompletionHistory", "id", id));

         courseCompletionHistoryRepository.delete(courseCompletionHistory);

            return ResponseEntity.ok().build();
    }

}
