package com.example.mypenavigatorapi.users.services;

import com.example.mypenavigatorapi.common.exceptions.BadRequestException;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.users.domain.dto.SaveMypeDto;
import com.example.mypenavigatorapi.users.domain.entities.Mype;
import com.example.mypenavigatorapi.users.domain.repositories.MypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MypeService {
    @Autowired
    private MypeRepository mypeRepository;

    public List<Mype> findAll() {
        return mypeRepository.findAll();
    }

    public Mype findById(Long id) {
        return mypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mype", "id", id));
    }

    public Mype save(SaveMypeDto dto) {
        Mype mype = Mapper.map(dto, Mype.class);

        mypeRepository.findByRuc(mype.getRuc())
                .ifPresent(m -> {
                    throw new BadRequestException("Mype with RUC " + m.getRuc() + " already exists");
                });

        return mypeRepository.save(mype);
    }

    public Mype update(Long id, SaveMypeDto dto){
        Mype mype = mypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mype", "id", id));

        mypeRepository.findByRuc(dto.getRuc())
                .ifPresent(m -> {
                    if(!m.getId().equals(mype.getId())) throw new BadRequestException("Mype with RUC " + m.getRuc() + " already exists");
                });

        mype.setName(dto.getName());
        mype.setRuc(dto.getRuc());

        return mypeRepository.save(mype);
    }

    public ResponseEntity<?> delete (Long id){
        Mype mype = mypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mype", "id", id));

        mypeRepository.delete(mype);

        return ResponseEntity.ok().build();
    }
}
