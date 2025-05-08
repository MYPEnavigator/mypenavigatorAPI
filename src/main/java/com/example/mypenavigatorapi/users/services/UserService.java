package com.example.mypenavigatorapi.users.services;

import com.example.mypenavigatorapi.common.exceptions.BadRequestException;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.SaveNotificationDto;
import com.example.mypenavigatorapi.communication.events.NewNotificationEvent;
import com.example.mypenavigatorapi.rewards.domain.entities.UserBankReward;
import com.example.mypenavigatorapi.users.domain.dto.SaveUserDto;
import com.example.mypenavigatorapi.users.domain.entities.Bank;
import com.example.mypenavigatorapi.users.domain.entities.Mype;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.domain.enums.Role;
import com.example.mypenavigatorapi.users.domain.repositories.BankRepository;
import com.example.mypenavigatorapi.users.domain.repositories.MypeRepository;
import com.example.mypenavigatorapi.users.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public List<User> findAll(Long bankId, Long mypeId) {
        if(bankId != 0) {
            return userRepository.findAllByBankId(bankId);
        }

        if (mypeId != 0) {
            return userRepository.findAllByMypeId(mypeId);
        }

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
        if(userRepository.findByDni(dto.getDni()).isPresent()) {
            throw new BadRequestException("El DNI ya está en uso");
        }

        if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("El correo ya está en uso");
        }


        Role role = Role.admin;

        Bank bank = null;
        if (bankId != 0) {
            bank = bankRepository.findById(bankId)
                    .orElseThrow(() -> new ResourceNotFoundException("Bank", "id", bankId));

            if (userRepository.findAllByBankId(bankId).isEmpty()) {
                role = Role.bank_admin;
            } else {
                role = Role.bank_user;
            }
        }

        Mype mype = null;
        if (mypeId != 0) {
            mype = mypeRepository.findById(mypeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Mype", "id", mypeId));

            if (userRepository.findAllByMypeId(mypeId).isEmpty()) {
                role = Role.mype_admin;
            } else {
                role = Role.mype_user;
            }
        }

        User user = Mapper.map(dto, User.class);
        user.setBank(bank);
        user.setMype(mype);
        user.setRole(role);
        user.setGoogleAccount(dto.getIsGoogleAccount());

        user.setPassword(passwordEncoder.encode(dto.getPassword()));


        return userRepository.save(user);
    }

    public User toggleActive(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setActive(!user.isActive());
        return userRepository.save(user);
    }

    public User update(Long id, SaveUserDto dto) {
        User existingUser = userRepository.findByDni(dto.getDni())
                .orElse(null);

        if(existingUser != null && !existingUser.getId().equals(id)) {
            throw new BadRequestException("El DNI ya está en uso");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        Mapper.merge(dto, user);

        if(dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return userRepository.save(user);
    }

    public ResponseEntity<?> delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    public void sendUserBankRewardNotification(Long bankId, UserBankReward userBankReward) {
        List<User> users = userRepository.findAllByBankId(bankId);

        users.forEach(user -> {
            SaveNotificationDto dto = new SaveNotificationDto();
            dto.setTitle("Se reclamó un premio");
            dto.setText("El usuario " + userBankReward.getUser().getName() + " reclamó el premio " + userBankReward.getBankReward().getName());
            eventPublisher.publishEvent(new NewNotificationEvent(this, user.getId(), dto, true));
        });
    }
}
