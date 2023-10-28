package com.brianeno.mapstruct.service.impl;

import com.brianeno.mapstruct.dto.UserDto;
import com.brianeno.mapstruct.entity.User;
import com.brianeno.mapstruct.exception.EmailAlreadyExistsException;
import com.brianeno.mapstruct.exception.ResourceNotFoundException;
import com.brianeno.mapstruct.mapper.UserMapper;
import com.brianeno.mapstruct.repository.UserRepository;
import com.brianeno.mapstruct.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDto createUser(UserDto userDto) {

    // Convert UserDto into User JPA Entity
    Optional<User> optionalUser = this.userRepository.findByEmail(userDto.getEmail());

    if (optionalUser.isPresent()) {
      throw new EmailAlreadyExistsException("Email Already Exists for User");
    }

    User user = UserMapper.MAPPER.mapToUser(userDto);

    User savedUser = this.userRepository.save(user);

    UserDto savedUserDto = UserMapper.MAPPER.mapToUserDto(savedUser);

    return savedUserDto;
  }

  @Override
  public UserDto getUserById(Long userId) {
    User user = this.userRepository.findById(userId).orElseThrow(
        () -> new ResourceNotFoundException("User", "id", userId)
    );
    return UserMapper.MAPPER.mapToUserDto(user);
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<User> users = this.userRepository.findAll();

    return users.stream().map(UserMapper.MAPPER::mapToUserDto)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto updateUser(UserDto user) {

    User existingUser = this.userRepository.findById(user.getId()).orElseThrow(
        () -> new ResourceNotFoundException("User", "id", user.getId())
    );

    existingUser.setFirstName(user.getFirstName());
    existingUser.setLastName(user.getLastName());
    existingUser.setEmail(user.getEmail());
    User updatedUser = this.userRepository.save(existingUser);
    return UserMapper.MAPPER.mapToUserDto(updatedUser);
  }

  @Override
  public void deleteUser(Long userId) {

    this.userRepository.findById(userId).orElseThrow(
        () -> new ResourceNotFoundException("User", "id", userId)
    );

    this.userRepository.deleteById(userId);
  }
}
