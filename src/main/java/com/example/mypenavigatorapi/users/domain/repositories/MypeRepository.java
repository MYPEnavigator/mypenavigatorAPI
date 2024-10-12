package com.example.mypenavigatorapi.users.domain.repositories;

import com.example.mypenavigatorapi.users.domain.entities.Mype;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MypeRepository extends JpaRepository<Mype, Long> {
    Optional<Mype> findByRuc(String ruc);
}
