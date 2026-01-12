package com.simplebank.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "DTO пользователя")
public record UserFullDto(
        @Schema(description = "id пользователя", example = "Иван")
        UUID id,

        @Schema(description = "Имя пользователя", example = "Иван")
        String firstName,

        @Schema(description = "Фамилия пользователя", example = "Петров")
        String lastName,

        @Schema(description = "Отчество пользователя", example = "Александорович")
        String middleName,

        @Schema(description = "Дата рождения", example = "1994-10-27")
        LocalDate birthday,

        @Schema(description = "Номер телефона", example = "8005553535")
        String phone,

        String avatarS3Key
) {
}
