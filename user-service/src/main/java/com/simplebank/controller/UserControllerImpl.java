package com.simplebank.controller;

import com.simplebank.controller.impl.UserController;
import com.simplebank.dto.response.UserFullDto;
import com.simplebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    @GetMapping("/{userId}")
    public UserFullDto getById(@PathVariable("userId") UUID userId) {
        return userService.getById(userId);
    }
}
