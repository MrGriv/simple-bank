package com.simplebank.mapper;

import com.simplebank.dto.request.UserCreateDto;
import com.simplebank.dto.response.UserFullDto;
import com.simplebank.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User ofUserCreateDto(UserCreateDto userCreateDto);

    UserFullDto ofEntity(User user);
}
