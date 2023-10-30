package com.brianeno.mapstruct.controller;

import com.brianeno.mapstruct.dto.ProjectDto;
import com.brianeno.mapstruct.dto.ProjectTypeDto;
import com.brianeno.mapstruct.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
    name = "CRUD REST APIs for Project Resource",
    description = "CRUD REST APIs - Create Project, Update Project, Get Project, Get All Projects, Delete Project"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/projects")
public class ProjectController {

  private final ProjectService projectService;

  @Operation(
      summary = "Create Project REST API",
      description = "Create Project REST API is used to save project in a database"
  )
  @ApiResponse(
      responseCode = "201",
      description = "HTTP Status 201 CREATED"
  )
  @PostMapping
  public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto project) {
    final var savedProject = this.projectService.createProject(project);
    return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Update Project REST API",
      description = "Update Project REST API is used to save project in a database"
  )
  @ApiResponse(
      responseCode = "201",
      description = "HTTP Status 201 CREATED"
  )
  @PutMapping
  public ResponseEntity<ProjectDto> updateProject(@Valid @RequestBody ProjectDto project) {
    final var savedProject = this.projectService.updateProject(project);
    return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Get Project By Id REST API",
      description = "Get Project By Id REST API is used to retrieve project by id in the database"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200"
  )
  @GetMapping("/{id}")
  public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
    final var projects = this.projectService.getProjectById(id);
    return new ResponseEntity<>(projects, HttpStatus.OK);
  }

  @Operation(
      summary = "Get All Projects REST API",
      description = "Get All Projects REST API is used to retrieve projects in the database"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200"
  )
  @GetMapping
  public ResponseEntity<List<ProjectDto>> getAllProjects() {
    final var projects = this.projectService.getAllProjects();
    return new ResponseEntity<>(projects, HttpStatus.OK);
  }

  @Operation(
      summary = "Create Project Type REST API",
      description = "Create Project Type REST API is used to save project typeX in a database"
  )
  @ApiResponse(
      responseCode = "201",
      description = "HTTP Status 201 CREATED"
  )
  @PostMapping("/types")
  public ResponseEntity<ProjectTypeDto> createProjectType(@Valid @RequestBody ProjectTypeDto projectTypeDto) {
    final var savedProjectTypeDto = this.projectService.createProjectType(projectTypeDto);
    return new ResponseEntity<>(savedProjectTypeDto, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Get All Project Typess REST API",
      description = "Get All Project Typess REST API is used to retrieve projects in the database"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200"
  )
  @GetMapping("/types")
  public ResponseEntity<List<ProjectTypeDto>> getAllProjectTypes() {
    final var projectTypes = this.projectService.getAllProjectTypes();
    return new ResponseEntity<>(projectTypes, HttpStatus.OK);
  }
}
