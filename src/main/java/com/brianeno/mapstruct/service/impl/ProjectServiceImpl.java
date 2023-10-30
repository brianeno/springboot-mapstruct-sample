package com.brianeno.mapstruct.service.impl;

import com.brianeno.mapstruct.dto.ProjectDto;
import com.brianeno.mapstruct.dto.ProjectTypeDto;
import com.brianeno.mapstruct.entity.Project;
import com.brianeno.mapstruct.exception.ResourceNotFoundException;
import com.brianeno.mapstruct.mapper.ProjectMapper;
import com.brianeno.mapstruct.mapper.ProjectTypeMapper;
import com.brianeno.mapstruct.repository.ProjectRepository;
import com.brianeno.mapstruct.repository.ProjectTypeRepository;
import com.brianeno.mapstruct.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;

  private final ProjectTypeRepository projectTypeRepository;

  private final ProjectMapper projectMapper;

  @Override
  public ProjectDto createProject(ProjectDto projectDto) {

    final var project = projectMapper.mapToProject(projectDto);

    final var savedProject = this.projectRepository.save(project);

    Optional<Project> optProject = this.projectRepository.findById(savedProject.getId());

    if (optProject.isEmpty()) {
      throw new ResourceNotFoundException("Project", "id", savedProject.getId());
    }
    final var savedProjectDto = projectMapper.mapToProjectDto(optProject.get());
    return savedProjectDto;
  }

  @Override
  public ProjectDto getProjectById(Long projectId) {
    final var project = this.projectRepository.findById(projectId).orElseThrow(
        () -> new ResourceNotFoundException("Project", "id", projectId)
    );
    return projectMapper.mapToProjectDto(project);
  }

  @Override
  public List<ProjectDto> getAllProjects() {
    final var projects = this.projectRepository.findAll();

    return projects.stream().map(projectMapper::mapToProjectDto)
        .collect(Collectors.toList());
  }

  @Override
  public ProjectDto updateProject(ProjectDto project) {

    final var existingProject = this.projectRepository.findById(project.getId()).orElseThrow(
        () -> new ResourceNotFoundException("Project", "id", project.getId())
    );

    existingProject.setName(project.getName());
    existingProject.setDescription(project.getDescription());
    existingProject.setStartDate(convertStrToTimestamp(project.getStartDate()));
    existingProject.setEndDate(convertStrToTimestamp(project.getEndDate()));
    final var updatedProject = this.projectRepository.save(existingProject);
    return projectMapper.mapToProjectDto(updatedProject);
  }

  protected Timestamp convertStrToTimestamp(String inDate) {
    Timestamp timestamp = null;
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date parsedDate = dateFormat.parse(inDate);
      timestamp = new java.sql.Timestamp(parsedDate.getTime());
    } catch (Exception e) { //this generic but you can control another types of exception
      e.printStackTrace();
    }
    return timestamp;
  }

  @Override
  public void deleteProject(Long projectId) {

    this.projectRepository.findById(projectId).orElseThrow(
        () -> new ResourceNotFoundException("Project", "id", projectId)
    );

    this.projectRepository.deleteById(projectId);
  }

  @Override
  public List<ProjectTypeDto> getAllProjectTypes() {
    final var projects = this.projectTypeRepository.findAll();

    return projects.stream().map(ProjectTypeMapper.PROJECT_TYPE_MAPPER_INSTANCE::mapToProjectTypeDto)
        .collect(Collectors.toList());
  }

  @Override
  public ProjectTypeDto createProjectType(ProjectTypeDto projectTypeDto) {

    final var projectType = ProjectTypeMapper.PROJECT_TYPE_MAPPER_INSTANCE.mapToProjectType(projectTypeDto);

    final var savedProjectType = this.projectTypeRepository.save(projectType);

    final var savedProjectTypeDto = ProjectTypeMapper.PROJECT_TYPE_MAPPER_INSTANCE.mapToProjectTypeDto(savedProjectType);
    return savedProjectTypeDto;
  }
}
