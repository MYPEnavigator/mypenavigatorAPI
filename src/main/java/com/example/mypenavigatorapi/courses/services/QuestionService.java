package com.example.mypenavigatorapi.courses.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.SaveQuestionDto;
import com.example.mypenavigatorapi.courses.domain.entities.Answer;
import com.example.mypenavigatorapi.courses.domain.entities.Question;
import com.example.mypenavigatorapi.courses.domain.entities.Test;
import com.example.mypenavigatorapi.courses.domain.repositories.AnswerRepository;
import com.example.mypenavigatorapi.courses.domain.repositories.QuestionRepository;
import com.example.mypenavigatorapi.courses.domain.repositories.TestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TestRepository testRepository;

    public List<Question> findAllByTestId(Long testId) {
        return questionRepository.findAllByTestId(testId);
    }

    @Transactional
    public Question save(SaveQuestionDto dto, Long testId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new ResourceNotFoundException("Test", "id", testId));

        Question question = Mapper.map(dto, Question.class);
        question.setTest(test);

        return getQuestionWithAnswers(dto, question);
    }

    public Question update(Long id, SaveQuestionDto dto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));

        question.setQuestionText(dto.getQuestionText());
        question.setMultiple(dto.getMultiple());

        return getQuestionWithAnswers(dto, question);
    }

    private Question getQuestionWithAnswers(SaveQuestionDto dto, Question question) {
        List<Answer> answers = new ArrayList<>();

        dto.getAnswers().forEach(answerDto -> {
            Answer answer = Mapper.map(answerDto, Answer.class);
            answer.setQuestion(question);
            answers.add(answer);
        });

        question.setAnswers(answers);
        return questionRepository.save(question);
    }

    public ResponseEntity<?> delete(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));

        questionRepository.delete(question);
        return ResponseEntity.ok().build();
    }
}
