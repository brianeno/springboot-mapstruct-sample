package com.brianeno.mapstruct.repository;

import com.brianeno.mapstruct.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  List<Project> findByManagerId(Long id);
}
