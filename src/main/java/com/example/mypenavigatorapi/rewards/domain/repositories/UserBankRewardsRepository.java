package com.example.mypenavigatorapi.rewards.domain.repositories;

import com.example.mypenavigatorapi.rewards.domain.entities.UserBankReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBankRewardsRepository extends JpaRepository<UserBankReward, Long> {
    List<UserBankReward> findAllByUserId(Long userId);
    List<UserBankReward> findAllByBankRewardId(Long bankRewardId);
}
