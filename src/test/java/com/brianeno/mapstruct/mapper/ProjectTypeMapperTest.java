package com.brianeno.mapstruct.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import com.brianeno.mapstruct.dto.ProjectTypeDto;
import com.brianeno.mapstruct.entity.ProjectType;
import org.junit.jupiter.api.Test;


class ProjectTypeMapperTest {

  @Test
  void mapToProjectTypeDto() {
    ProjectType projectType = new ProjectType();
    projectType.setId(1L);
    projectType.setName("Test");
    ProjectTypeDto dto = ProjectTypeMapper.PROJECT_TYPE_MAPPER_INSTANCE.mapToProjectTypeDto(projectType);
    assertThat(dto).isNotNull();
    assertThat(dto.getName()).isEqualTo(projectType.getName());
  }

  @Test
  void mapToProjectType() {
    ProjectTypeDto dto = new ProjectTypeDto();
    dto.setId(1L);
    dto.setName("Test");
    ProjectType projectType = ProjectTypeMapper.PROJECT_TYPE_MAPPER_INSTANCE.mapToProjectType(dto);
    assertThat(projectType).isNotNull();
    assertThat(projectType.getName()).isEqualTo(dto.getName());  }
}