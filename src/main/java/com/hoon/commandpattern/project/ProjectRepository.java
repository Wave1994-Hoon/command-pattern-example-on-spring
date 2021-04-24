package com.hoon.commandpattern.project;

import com.hoon.commandpattern.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
