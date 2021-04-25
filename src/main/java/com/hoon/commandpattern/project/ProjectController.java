package com.hoon.commandpattern.project;

import com.hoon.commandpattern.project.model.Project;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectStatusService projectStatusService;

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> setProjectStatusIsProceed(@PathVariable long projectId) {
        return ResponseEntity
                .ok(projectStatusService.setProjectStatusIsProceed(projectId));
    }
}
