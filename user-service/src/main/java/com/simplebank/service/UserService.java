package com.simplebank.service;

import com.simplebank.dto.request.AuthenticationRequestDto;
import com.simplebank.dto.request.UserCreateDto;
import com.simplebank.dto.response.UserFullDto;

import java.util.UUID;

public interface UserService {
    UserFullDto authenticate(AuthenticationRequestDto request);

    UUID register(UserCreateDto newUser);

    UserFullDto getById(UUID userId);
}
