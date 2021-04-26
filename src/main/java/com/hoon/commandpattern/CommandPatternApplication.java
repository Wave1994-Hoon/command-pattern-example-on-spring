package com.hoon.commandpattern;

import com.hoon.commandpattern.project.ProjectRepository;
import com.hoon.commandpattern.project.model.Project;
import com.hoon.commandpattern.project.model.ProjectStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class CommandPatternApplication {

    private final ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(CommandPatternApplication.class, args);
    }

    @PostConstruct
    public void initDate() {
        Project project1 = Project.builder()
                .name("Project1")
                .status(ProjectStatusType.REGISTERED)
                .build();

        Project project2 = Project.builder()
                .name("Project1")
                .status(ProjectStatusType.PROCEED)
                .build();

        Project project3 = Project.builder()
                .name("Project1")
                .status(ProjectStatusType.REJECT)
                .build();

        projectRepository.save(project1);
        projectRepository.save(project2);
        projectRepository.save(project3);
    }
}
