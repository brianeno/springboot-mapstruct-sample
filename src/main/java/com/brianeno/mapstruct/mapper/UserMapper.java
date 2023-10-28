package com.brianeno.mapstruct.mapper;

import com.brianeno.mapstruct.dto.UserDto;
import com.brianeno.mapstruct.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

  UserDto mapToUserDto(User user);

  User mapToUser(UserDto userDto);
}
