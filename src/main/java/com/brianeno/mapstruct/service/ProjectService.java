package com.brianeno.mapstruct.service;

import com.brianeno.mapstruct.dto.ProjectDto;
import com.brianeno.mapstruct.dto.ProjectTypeDto;

import java.util.List;

public interface ProjectService {
  ProjectDto createProject(ProjectDto projectDto);

  ProjectDto getProjectById(Long projectId);

  List<ProjectDto> getAllProjects();

  ProjectDto updateProject(ProjectDto projectDto);

  void deleteProject(Long userId);

  List<ProjectTypeDto> getAllProjectTypes();

  ProjectTypeDto createProjectType(ProjectTypeDto user);
}
