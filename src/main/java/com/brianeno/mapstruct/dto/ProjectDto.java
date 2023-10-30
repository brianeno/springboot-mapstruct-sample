package com.brianeno.mapstruct.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
    description = "ProjectDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

  private Long id;

  @Schema(
      description = "Project Name"
  )
  @NotEmpty(message = "Project name can not be null or empty")
  private String name;

  @Schema(
      description = "Project description"
  )
  private String description;

  @Schema(
      description = "Project Start Date"
  )
  @NotEmpty(message = "Project start date cannot be null or empty")
  private String startDate;

  @Schema(
      description = "Project Completion Date"
  )
  @NotEmpty(message = "Project completion date cannot be null or empty")
  private String endDate;

  @Schema(
      description = "Project Manager ID"
  )
  private Long managerId;
}
