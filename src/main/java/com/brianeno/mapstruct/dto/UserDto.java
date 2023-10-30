package com.brianeno.mapstruct.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(
    description = "UserDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Long id;

  @Schema(
      description = "User First Name"
  )
  @NotEmpty(message = "User first name should not be null or empty")
  private String firstName;

  @Schema(
      description = "User Last Name"
  )
  @NotEmpty(message = "User last name should not be null or empty")
  private String lastName;

  @Schema(
      description = "User Position"
  )
  @NotEmpty(message = "User position should not be null or empty")
  private String position;

  @Schema(
      description = "User Email Address"
  )
  @NotEmpty(message = "User email should not be null or empty")
  @Email(message = "Email address should be valid")
  private String email;

  @Schema(
      description = "Projects managed by this user"
  )
  private List<ProjectDto> projects;
}
