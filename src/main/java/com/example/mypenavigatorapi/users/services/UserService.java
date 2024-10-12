package com.example.mypenavigatorapi.users.services;

import com.example.mypenavigatorapi.common.exceptions.BadRequestException;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.users.domain.dto.SaveUserDto;
import com.example.mypenavigatorapi.users.domain.entities.Bank;
import com.example.mypenavigatorapi.users.domain.entities.Mype;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.domain.repositories.BankRepository;
import com.example.mypenavigatorapi.users.domain.repositories.MypeRepository;
import com.example.mypenavigatorapi.users.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private MypeRepository mypeRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    public User save(SaveUserDto dto, Long bankId, Long mypeId) {
        if (bankId != 0 && mypeId != 0) {
            throw new BadRequestException("User cannot be associated with both a bank and a mype");
        }

        Bank bank = null;
        if (bankId != 0) {
            bank = bankRepository.findById(bankId)
                    .orElseThrow(() -> new ResourceNotFoundException("Bank", "id", bankId));
        }

        Mype mype = null;
        if (mypeId != 0) {
            mype = mypeRepository.findById(mypeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Mype", "id", mypeId));
        }

        User user = Mapper.map(dto, User.class);
        user.setBank(bank);
        user.setMype(mype);

        return userRepository.save(user);
    }

    public User update(Long id, SaveUserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setDni(dto.getDni());

        return userRepository.save(user);
    }

    public ResponseEntity<?> delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}
