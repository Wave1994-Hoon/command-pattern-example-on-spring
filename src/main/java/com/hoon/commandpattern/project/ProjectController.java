package com.hoon.commandpattern.project;

import com.hoon.commandpattern.project.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectStatusService projectStatusService;

    @PatchMapping("/registered/{projectId}")
    public ResponseEntity<Project> setProjectStatusIsRegistered(@PathVariable long projectId) {
        return ResponseEntity
                .ok(projectStatusService.setProjectStatusIsRegistered(projectId));
    }

    @PatchMapping("/proceed/{projectId}")
    public ResponseEntity<Project> setProjectStatusIsProceed(@PathVariable long projectId) {
        return ResponseEntity
                .ok(projectStatusService.setProjectStatusIsProceed(projectId));
    }

    @PatchMapping("/reject/{projectId}")
    public ResponseEntity<Project> setProjectStatusIsReject(@PathVariable long projectId) {
        return ResponseEntity
                .ok(projectStatusService.setProjectStatusIsReject(projectId));
    }

    @PatchMapping("/done/{projectId}")
    public ResponseEntity<Project> setProjectStatusIsDone(@PathVariable long projectId) {
        return ResponseEntity
                .ok(projectStatusService.setProjectStatusIsDone(projectId));
    }
}
