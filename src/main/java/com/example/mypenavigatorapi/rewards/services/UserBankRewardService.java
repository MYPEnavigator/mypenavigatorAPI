package com.example.mypenavigatorapi.rewards.services;

import com.example.mypenavigatorapi.common.exceptions.BadRequestException;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.rewards.domain.entities.BankReward;
import com.example.mypenavigatorapi.rewards.domain.entities.UserBankPoints;
import com.example.mypenavigatorapi.rewards.domain.entities.UserBankReward;
import com.example.mypenavigatorapi.rewards.domain.repositories.BankRewardRepository;
import com.example.mypenavigatorapi.rewards.domain.repositories.UserBankPointsRepository;
import com.example.mypenavigatorapi.rewards.domain.repositories.UserBankRewardsRepository;
import com.example.mypenavigatorapi.users.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserBankRewardService {
    @Autowired
    private UserBankRewardsRepository userBankRewardsRepository;

    @Autowired
    private UserBankPointsRepository userBankPointsRepository;

    @Autowired
    private BankRewardRepository bankRewardRepository;

    @Autowired
    private UserService userService;


    public List<UserBankReward> findAllByUserId(Long userId) {
        return userBankRewardsRepository.findAllByUserId(userId);
    }

    public List<UserBankReward> findAllByBankRewardId(Long rewardId) {
        return userBankRewardsRepository.findAllByBankRewardId(rewardId);
    }

    public UserBankReward findById(Long id) {
        return userBankRewardsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserBankRewards", "id", id));
    }


    @Transactional
    public UserBankReward claimReward(Long userId, Long rewardId) {
        BankReward bankReward = bankRewardRepository.findById(rewardId)
                .orElseThrow(() -> new ResourceNotFoundException("BankReward", "id", rewardId));


        UserBankPoints userBankPoints = userBankPointsRepository
                .findByUserIdAndBankId(userId, bankReward.getBank().getId())
                .orElseThrow(() -> new BadRequestException("User does not have enough points to claim this reward"));

        if (userBankPoints.getPoints() < bankReward.getRequiredPoints()) {
            throw new BadRequestException("User does not have enough points to claim this reward");
        }

        userBankPoints.setPoints(userBankPoints.getPoints() - bankReward.getRequiredPoints());

        userBankPointsRepository.save(userBankPoints);

        UserBankReward userBankReward = new UserBankReward();
        userBankReward.setUser(userBankPoints.getUser());
        userBankReward.setBankReward(bankReward);
        userBankReward.setEarnedAt(new Date());

        UserBankReward userBankRewardSaved = userBankRewardsRepository.save(userBankReward);

        userService.sendUserBankRewardNotification(bankReward.getBank().getId(), userBankRewardSaved);

        return userBankRewardSaved;
    }
}
