package com.example.mypenavigatorapi.courses.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.SaveTestDto;
import com.example.mypenavigatorapi.courses.domain.entities.Answer;
import com.example.mypenavigatorapi.courses.domain.entities.Question;
import com.example.mypenavigatorapi.courses.domain.entities.Test;
import com.example.mypenavigatorapi.courses.domain.entities.Module;
import com.example.mypenavigatorapi.courses.domain.repositories.ModuleRepository;
import com.example.mypenavigatorapi.courses.domain.repositories.TestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Transactional
    public Test save(SaveTestDto dto, Long moduleId){
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module", "id", moduleId));

        Test test = Mapper.map(dto, Test.class);
        test.setModule(module);

        List<Question> questions = new ArrayList<>();

        dto.getQuestions().forEach(questionDto -> {
            Question question = Mapper.map(questionDto, Question.class);
            question.setTest(test);

            List<Answer> answers = new ArrayList<>();

            questionDto.getAnswers().forEach(answerDto -> {
                Answer answer = Mapper.map(answerDto, Answer.class);
                answer.setQuestion(question);

                answers.add(answer);
            });

            question.setAnswers(answers);
            questions.add(question);
        });

        test.setQuestions(questions);

        return testRepository.save(test);
    }

    @Transactional
    public Test update(Long id, SaveTestDto dto) {
        Test existingTest = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test", "id", id));

        existingTest.setTitle(dto.getTitle());

        existingTest.getQuestions().clear();

        List<Question> updatedQuestions = new ArrayList<>();

        dto.getQuestions().forEach(questionDto -> {
            Question question = new Question();
            question.setQuestionText(questionDto.getQuestionText());
            question.setMultiple(questionDto.getMultiple());
            question.setTest(existingTest);

            List<Answer> updatedAnswers = new ArrayList<>();
            questionDto.getAnswers().forEach(answerDto -> {
                Answer answer = new Answer();
                answer.setAnswerText(answerDto.getAnswerText());
                answer.setIsCorrect(answerDto.getIsCorrect());
                answer.setQuestion(question);

                updatedAnswers.add(answer);
            });

            question.setAnswers(updatedAnswers);
            updatedQuestions.add(question);
        });

        existingTest.getQuestions().addAll(updatedQuestions);

        return testRepository.save(existingTest);
    }



    public ResponseEntity<?> delete(Long id){
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test", "id", id));

        testRepository.delete(test);

        return ResponseEntity.ok().build();
    }
}
