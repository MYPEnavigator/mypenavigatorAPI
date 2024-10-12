package com.example.mypenavigatorapi.rewards.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.rewards.domain.dto.SaveBankRewardDto;
import com.example.mypenavigatorapi.rewards.domain.entities.BankReward;
import com.example.mypenavigatorapi.rewards.domain.repositories.BankRewardRepository;
import com.example.mypenavigatorapi.users.domain.entities.Bank;
import com.example.mypenavigatorapi.users.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankRewardService {
    @Autowired
    private BankRewardRepository bankRewardRepository;

    @Autowired
    private BankService bankService;

    public List<BankReward> findAllByBankId(Long bankId) {
        return bankRewardRepository.findAllByBankId(bankId);
    }

    public BankReward findById(Long id) {
        return bankRewardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BankReward", "id", id));
    }

    public BankReward save(SaveBankRewardDto dto, Long bankId) {
        Bank bank = bankService.findById(bankId);
        BankReward bankReward = Mapper.map(dto, BankReward.class);

        bankReward.setBank(bank);
        return bankRewardRepository.save(bankReward);
    }

    public BankReward update(Long id, SaveBankRewardDto dto) {
        BankReward bankReward = bankRewardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BankReward", "id", id));

        Mapper.merge(dto, bankReward);
        return bankRewardRepository.save(bankReward);
    }

    public ResponseEntity<?> delete(Long id) {
        BankReward bankReward = bankRewardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BankReward", "id", id));

        bankRewardRepository.delete(bankReward);
        return ResponseEntity.ok().build();
    }

}
