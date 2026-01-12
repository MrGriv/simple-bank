package com.simplebank.controller.impl;

import com.simplebank.dto.request.AuthenticationRequestDto;
import com.simplebank.dto.request.UserCreateDto;
import com.simplebank.dto.response.UserFullDto;
import com.simplebank.handler.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.util.UUID;

public interface AuthenticationController {
    @Operation(
            summary = "Авторизация пользователя по логину и паролю"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь авторизован",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserFullDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные параметры запроса",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "\"Неверные учетные данные\"",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Сервис временно недоступен. Повторите попытку позже.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    UserFullDto authenticate(AuthenticationRequestDto request);

    @Operation(
            summary = "Регистрация нового пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Пользователь зарегистрирован",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UUID.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные параметры запроса",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Сервис временно недоступен. Повторите попытку позже.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    UUID register(UserCreateDto newUser);
}
