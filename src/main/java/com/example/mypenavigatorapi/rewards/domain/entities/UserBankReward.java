package com.example.mypenavigatorapi.rewards.domain.entities;

import com.example.mypenavigatorapi.common.domain.entity.AuditModel;
import com.example.mypenavigatorapi.users.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user_bank_rewards")
public class UserBankReward extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "earned_at")
    private Date earnedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bank_reward_id")
    private BankReward bankReward;

}
