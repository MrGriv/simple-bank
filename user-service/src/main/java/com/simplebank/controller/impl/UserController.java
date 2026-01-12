package com.simplebank.controller.impl;

import com.simplebank.dto.response.UserFullDto;
import com.simplebank.handler.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.util.UUID;

public interface UserController {
    @Operation(
            summary = "Получение пользователя по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь получен",
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
                    responseCode = "404",
                    description = "Пользователь не найден",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Сервис временно недоступен. Повторите попытку позже.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    UserFullDto getById(UUID userId);
}
