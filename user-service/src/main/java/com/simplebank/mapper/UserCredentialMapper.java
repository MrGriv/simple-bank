package com.simplebank.mapper;

import com.simplebank.model.User;
import com.simplebank.model.UserCredential;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCredentialMapper {
    @Mapping(target = "id", ignore = true)
    UserCredential ofUserCreateDto(String email, String password, User user);
}
