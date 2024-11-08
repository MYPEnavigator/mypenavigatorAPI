package com.example.mypenavigatorapi.rewards.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.rewards.domain.dto.SaveBankRewardDto;
import com.example.mypenavigatorapi.rewards.domain.dto.SaveUserBankPointsDto;
import com.example.mypenavigatorapi.rewards.domain.entities.UserBankPoints;
import com.example.mypenavigatorapi.rewards.domain.repositories.UserBankPointsRepository;
import com.example.mypenavigatorapi.users.domain.entities.Bank;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.BankService;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBankPointsService {
    @Autowired
    private UserBankPointsRepository userBankPointsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BankService bankService;

    public List<UserBankPoints> findAllByUserId(Long userId) {
        return userBankPointsRepository.findAllByUserId(userId);
    }

    public UserBankPoints findByUserIdAndBankId(Long userId, Long bankId) {
        return userBankPointsRepository.findByUserIdAndBankId(userId, bankId)
                .orElseThrow(() -> new ResourceNotFoundException("UserBankPoints", "userId", userId));
    }

    public UserBankPoints create(Long userId, Long bankId){
        User user = userService.findById(userId);
        Bank bank = bankService.findById(bankId);

        UserBankPoints userBankPoints = new UserBankPoints();
        userBankPoints.setUser(user);
        userBankPoints.setBank(bank);
        userBankPoints.setPoints(0);

        return userBankPointsRepository.save(userBankPoints);
    }

    public UserBankPoints earnPoints(Long userId, Long bankId, SaveUserBankPointsDto dto) {
        UserBankPoints userBankPoints = userBankPointsRepository
                .findByUserIdAndBankId(userId, bankId)
                .orElseGet(() -> create(userId, bankId));

        userBankPoints.setPoints(userBankPoints.getPoints() + dto.getPoints());
        return userBankPointsRepository.save(userBankPoints);
    }
    public ResponseEntity<?> delete(Long id) {
        UserBankPoints userBankPoints = userBankPointsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserBankPoints", "id", id));

        userBankPointsRepository.delete(userBankPoints);
        return ResponseEntity.ok().build();
    }
}
