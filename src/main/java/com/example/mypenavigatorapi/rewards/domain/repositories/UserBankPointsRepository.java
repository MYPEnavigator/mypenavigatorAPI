package com.example.mypenavigatorapi.rewards.domain.repositories;

import com.example.mypenavigatorapi.rewards.domain.entities.UserBankPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBankPointsRepository extends JpaRepository<UserBankPoints, Long> {
    List<UserBankPoints> findAllByUserId(Long userId);
    List<UserBankPoints> findAllByBankId(Long bankId);

    Optional<UserBankPoints> findByUserIdAndBankId(Long userId, Long bankId);
}
