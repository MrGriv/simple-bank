package com.simplebank.controller;

import com.simplebank.controller.impl.AuthenticationController;
import com.simplebank.dto.request.AuthenticationRequestDto;
import com.simplebank.dto.request.UserCreateDto;
import com.simplebank.dto.response.UserFullDto;
import com.simplebank.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationControllerImpl implements AuthenticationController {
    private final UserService userService;

    @Override
    @PostMapping("/login")
    public UserFullDto authenticate(@Valid @RequestBody AuthenticationRequestDto request) {
        return userService.authenticate(request);
    }

    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID register(@Valid @RequestBody UserCreateDto newUser) {
        return userService.register(newUser);
    }
}
