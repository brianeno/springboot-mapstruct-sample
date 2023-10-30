package com.brianeno.mapstruct.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
    description = "ProjectTypeDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTypeDto {

  private Long id;

  @Schema(
      description = "Project Type Name"
  )
  @NotEmpty(message = "Project type name can not be null or empty")
  private String name;
}
