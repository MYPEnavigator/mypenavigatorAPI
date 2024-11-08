package com.example.mypenavigatorapi.communication.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.SaveGeneralSurveyDto;
import com.example.mypenavigatorapi.communication.domain.entities.GeneralSurvey;
import com.example.mypenavigatorapi.communication.domain.repositories.GeneralSurveyRepository;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralSurveyService {
    @Autowired
    private GeneralSurveyRepository generalSurveyRepository;

    @Autowired
    private UserService userService;

    public List<GeneralSurvey> findAll() {
        return generalSurveyRepository.findAll();
    }

    public GeneralSurvey findByUserId(Long userId) {
        return generalSurveyRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("GeneralSurvey", "userId", userId));
    }

    public GeneralSurvey create(SaveGeneralSurveyDto dto, Long userId) {
        User user = userService.findById(userId);

        GeneralSurvey generalSurvey = generalSurveyRepository.findByUserId(userId)
                .orElseGet(() -> {
                    GeneralSurvey newGeneralSurvey = new GeneralSurvey();
                    newGeneralSurvey.setUser(user);
                    return newGeneralSurvey;
                });

        Mapper.merge(dto, generalSurvey);

        return generalSurveyRepository.save(generalSurvey);
    }
}
