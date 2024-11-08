package com.example.mypenavigatorapi.enrollments.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.entities.Course;
import com.example.mypenavigatorapi.courses.services.CourseService;
import com.example.mypenavigatorapi.enrollments.domain.dto.SaveSurveyDto;
import com.example.mypenavigatorapi.enrollments.domain.entities.Enrollment;
import com.example.mypenavigatorapi.enrollments.domain.entities.Survey;
import com.example.mypenavigatorapi.enrollments.domain.entities.SurveyQuestionResponse;
import com.example.mypenavigatorapi.enrollments.domain.repositories.SurveyRepository;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    public List<Survey> findAllByCourseId(Long courseId) {
        return surveyRepository.findAllByCourseId(courseId);
    }

    public Survey findByUserIdAndCourseId(Long userId, Long courseId) {
        return surveyRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Survey", "userId and courseId", userId + " and " + courseId));
    }

    @Transactional
    public Survey save(SaveSurveyDto dto, Long userId, Long courseId) {
        User user = userService.findById(userId);
        Course course = courseService.findById(courseId);

        Survey survey = new Survey();
        survey.setUser(user);
        survey.setCourse(course);

        List<SurveyQuestionResponse> responses = dto.getResponses().stream()
                .map(responseDto -> {
                    SurveyQuestionResponse response = Mapper.map(responseDto, SurveyQuestionResponse.class);
                    response.setSurvey(survey);
                    return response;
                })
                .toList();

        survey.setResponses(responses);
        return surveyRepository.save(survey);
    }

    public Survey findById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey", "id", id));
    }

    public ResponseEntity<?> delete(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey", "id", id));

        surveyRepository.delete(survey);

        return ResponseEntity.ok().build();
    }


}
