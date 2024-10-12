package com.example.mypenavigatorapi.users.domain.repositories;

import com.example.mypenavigatorapi.users.domain.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findByRuc(String ruc);
}
