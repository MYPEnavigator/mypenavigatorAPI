package com.example.mypenavigatorapi.rewards.domain.repositories;

import com.example.mypenavigatorapi.rewards.domain.entities.BankReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRewardRepository extends JpaRepository<BankReward, Long> {
    List<BankReward> findAllByBankId(Long bankId);
}
