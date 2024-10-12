package com.example.mypenavigatorapi.users.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.users.domain.dto.SaveUserDto;
import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import com.example.mypenavigatorapi.users.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "users", description = "users endpoints")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all users")
    public List<UserDto> findAll() {
        return userService.findAll().stream()
                .map(user -> Mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public UserDto findById(@PathVariable("id") Long id) {
        return Mapper.map(userService.findById(id), UserDto.class);
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "User must be associated with a bank or a mype, not both")
    public UserDto save(
            @Valid @RequestBody SaveUserDto dto,
            @RequestParam(value = "bankId", defaultValue = "0") Long bankId,
            @RequestParam(value = "mypeId", defaultValue = "0") Long mypeId
    ) {
        return Mapper.map(userService.save(dto, bankId, mypeId), UserDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update user by id")
    public UserDto update(
            @PathVariable("id") Long id,
            @Valid @RequestBody SaveUserDto dto
    ) {
        return Mapper.map(userService.update(id, dto), UserDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
