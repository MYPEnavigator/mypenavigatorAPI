package com.example.mypenavigatorapi.communication.domain.repositories;

import com.example.mypenavigatorapi.communication.domain.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
