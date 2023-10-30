package com.brianeno.mapstruct.mapper;

import com.brianeno.mapstruct.dto.UserDto;
import com.brianeno.mapstruct.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {ProjectMapper.class})
public interface UserMapper {

  @Mapping(target = "position", source = "positionDescription")
  @Mapping(target = "projects", source = "projects")
  UserDto mapToUserDto(User user);

  @Mapping(target = "positionDescription", source = "position")
  User mapToUser(UserDto userDto);

  default User fromId(final Long id) {

    if (id == null) {
      return null;
    }

    final User user = new User();

    user.setId(id);

    return user;
  }
}
