package com.brianeno.mapstruct.mapper;

import com.brianeno.mapstruct.dto.ProjectTypeDto;
import com.brianeno.mapstruct.entity.ProjectType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectTypeMapper {

  ProjectTypeMapper PROJECT_TYPE_MAPPER_INSTANCE = Mappers.getMapper(ProjectTypeMapper.class);

  ProjectTypeDto mapToProjectTypeDto(ProjectType projectType);

  ProjectType mapToProjectType(ProjectTypeDto project);
}
