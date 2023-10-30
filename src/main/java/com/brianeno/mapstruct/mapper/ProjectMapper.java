package com.brianeno.mapstruct.mapper;

import com.brianeno.mapstruct.dto.ProjectDto;
import com.brianeno.mapstruct.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {UserMapper.class})
public interface ProjectMapper extends BaseMapper {

  @Mapping(source = "startDate",
      target = "startDate",
      dateFormat = "dd/MMM/yyyy")
  @Mapping(source = "endDate",
      target = "endDate",
      dateFormat = "dd/MMM/yyyy")
  @Mapping(source = "manager.id", target = "managerId")
  ProjectDto mapToProjectDto(Project project);
  List<ProjectDto> toDto(final List<Project> projects);

  @Mapping(source = "startDate",
      target = "startDate",
      dateFormat = "dd/MMM/yyyy")
  @Mapping(source = "endDate",
      target = "endDate",
      dateFormat = "dd/MMM/yyyy")
  @Mapping(source = "managerId", target = "manager")
  Project mapToProject(ProjectDto project);

  List<Project> toEntity(final List <ProjectDto> projectDtos);

  default Project fromId(final Long id) {

    if (id == null) {
      return null;
    }

    final Project project = new Project();
    project.setId(id);

    return project;
  }
}
