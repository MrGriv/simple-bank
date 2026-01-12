package com.simplebank.service.impl;

import com.simplebank.dto.request.AuthenticationRequestDto;
import com.simplebank.dto.request.UserCreateDto;
import com.simplebank.dto.response.UserFullDto;
import com.simplebank.exception.BadCredentialsException;
import com.simplebank.exception.NotFoundException;
import com.simplebank.mapper.UserCredentialMapper;
import com.simplebank.mapper.UserMapper;
import com.simplebank.model.User;
import com.simplebank.model.UserCredential;
import com.simplebank.repository.UserCredentialRepository;
import com.simplebank.repository.UserRepository;
import com.simplebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final UserCredentialMapper userCredentialMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserFullDto authenticate(AuthenticationRequestDto request) {
        UserCredential userCredential = userCredentialRepository.findByEmail(request.email())
                .orElseThrow(
                        () -> new BadCredentialsException("Неверные учетные данные")
                );

        if (!passwordEncoder.matches(request.password(), userCredential.getPassword())) {
            throw new BadCredentialsException("Неверные учетные данные");
        }

        return userMapper.ofEntity(userCredential.getUser());
    }

    @Override
    public UUID register(UserCreateDto newUser) {
        User user = userMapper.ofUserCreateDto(newUser);
        userRepository.saveAndFlush(user);

        String encodedPassword = passwordEncoder.encode(newUser.password());
        UserCredential userCredential = userCredentialMapper.ofUserCreateDto(newUser.email(), encodedPassword, user);
        userCredentialRepository.save(userCredential);

        return user.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public UserFullDto getById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::ofEntity)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id = %s", userId)));
    }
}
