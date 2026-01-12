package com.simplebank.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Данные для входа")
public record AuthenticationRequestDto(
        @Email(message = "Неверный формат почты")
        @NotBlank(message = "Почта обязательна")
        @Schema(description = "Почта", example = "ivan@mail.ru")
        String email,

        @NotBlank(message = "Пароль обязателен")
        @Schema(description = "Пароль", example = "qwerty123")
        @Size(min = 8, message = "Пароль должен содержать не меньше 8 символов")
        String password) {
}
