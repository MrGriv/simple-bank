package com.simplebank.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(description = "DTO создания пользователя")
public record UserCreateDto(
        @NotBlank(message = "Имя обязательно")
        @Size(max = 50, message = "Имя должно содержать до 50 символов")
        @Schema(description = "Имя пользователя", example = "Иван")
        String firstName,

        @NotBlank(message = "Фамилия обязательна")
        @Size(max = 50, message = "Фамилия должна содержать до 50 символов")
        @Schema(description = "Фамилия пользователя", example = "Петров")
        String lastName,

        @Size(max = 50, message = "Отчество должно содержать до 50 символов")
        @Schema(description = "Отчество пользователя", example = "Александорович")
        String middleName,

        @NotNull(message = "Дата рождения обязательна")
        @Past(message = "Дата рождения должна быть в прошлом")
        @Schema(description = "Дата рождения", example = "1994-10-27")
        LocalDate birthday,

        @NotBlank(message = "Номер телефона обязателен")
        @Pattern(
                regexp = "^\\d{10}$",
                message = "Номер телефона должен состоять из 10 цифр"
        )
        @Schema(description = "Номер телефона", example = "8005553535")
        String phone,

        @Email(message = "Неверный формат почты")
        @NotBlank(message = "Почта обязательна")
        @Schema(description = "Почта", example = "ivan@mail.ru")
        String email,

        @NotBlank(message = "Пароль обязателен")
        @Schema(description = "Пароль", example = "qwerty123")
        @Size(min = 8, message = "Пароль должен содержать не меньше 8 символов")
        String password) {
}
