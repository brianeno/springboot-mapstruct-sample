package com.brianeno.mapstruct.service.impl;

import com.brianeno.mapstruct.dto.UserDto;
import com.brianeno.mapstruct.entity.Project;
import com.brianeno.mapstruct.entity.User;
import com.brianeno.mapstruct.exception.EmailAlreadyExistsException;
import com.brianeno.mapstruct.exception.ResourceNotFoundException;
import com.brianeno.mapstruct.mapper.ProjectMapper;
import com.brianeno.mapstruct.mapper.UserMapper;
import com.brianeno.mapstruct.repository.ProjectRepository;
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
  private final ProjectRepository projectRepository;
  private final UserMapper userMapper;

  @Override
  public UserDto createUser(UserDto userDto) {

    // Convert UserDto into User JPA Entity
    Optional<User> optionalUser = this.userRepository.findByEmail(userDto.getEmail());

    if (optionalUser.isPresent()) {
      throw new EmailAlreadyExistsException("Email Already Exists for User");
    }

    final var user = userMapper.mapToUser(userDto);

    User savedUser = this.userRepository.save(user);

    final var savedUserDto = userMapper.mapToUserDto(savedUser);

    return savedUserDto;
  }

  @Override
  public UserDto getUserById(Long userId) {
    final var user = this.userRepository.findById(userId).orElseThrow(
        () -> new ResourceNotFoundException("User", "id", userId)
    );
    // let's load the projects for this user
    user.setProjects(this.projectRepository.findByManagerId(user.getId()));
    return userMapper.mapToUserDto(user);
  }

  @Override
  public List<UserDto> getAllUsers() {
    final var users = this.userRepository.findAll();

    // let's load the projects for each user
    //users.forEach(u -> u.setProjects(this.projectRepository.findByManagerId(u.getId())));

    return users.stream().map(userMapper::mapToUserDto)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto updateUser(UserDto user) {

    final var existingUser = this.userRepository.findById(user.getId()).orElseThrow(
        () -> new ResourceNotFoundException("User", "id", user.getId())
    );

    existingUser.setFirstName(user.getFirstName());
    existingUser.setLastName(user.getLastName());
    existingUser.setEmail(user.getEmail());
    final var updatedUser = this.userRepository.save(existingUser);
    return userMapper.mapToUserDto(updatedUser);
  }

  @Override
  public void deleteUser(Long userId) {

    List<Project> projects = this.projectRepository.findByManagerId(userId);
    if (!projects.isEmpty()) {
      throw new RuntimeException("Cannot delete, projects exist for this user " + userId);
    }
    this.userRepository.findById(userId).orElseThrow(
        () -> new ResourceNotFoundException("User", "id", userId)
    );

    this.userRepository.deleteById(userId);
  }
}
