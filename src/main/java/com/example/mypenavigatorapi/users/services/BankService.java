package com.example.mypenavigatorapi.users.services;

import com.example.mypenavigatorapi.common.exceptions.BadRequestException;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.users.domain.dto.BankDto;
import com.example.mypenavigatorapi.users.domain.dto.SaveBankDto;
import com.example.mypenavigatorapi.users.domain.entities.Bank;
import com.example.mypenavigatorapi.users.domain.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    public Bank findById(Long id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bank", "id", id));
    }

    public Bank save(SaveBankDto dto) {
        Bank bank = Mapper.map(dto, Bank.class);

        bankRepository.findByRuc(bank.getRuc())
                .ifPresent(b -> {
                    throw new BadRequestException("Bank with RUC " + b.getRuc() + " already exists");
                });


        return bankRepository.save(bank);
    }

    public Bank update(Long id, SaveBankDto dto){
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bank", "id", id));

        bankRepository.findByRuc(dto.getRuc())
                .ifPresent(b -> {
                    if(!b.getId().equals(bank.getId())) throw new BadRequestException("Bank with RUC " + b.getRuc() + " already exists");
                });


        bank.setName(dto.getName());
        bank.setAddress(dto.getAddress());
        bank.setRuc(dto.getRuc());

        return bankRepository.save(bank);
    }

    public ResponseEntity<?> delete (Long id){
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bank", "id", id));

        bankRepository.delete(bank);

        return ResponseEntity.ok().build();
    }

}
