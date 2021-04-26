package com.hoon.commandpattern.project;

import com.hoon.commandpattern.project.command.*;
import com.hoon.commandpattern.project.core.ProjectCommand;
import com.hoon.commandpattern.project.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ProjectStatusService {

    private final List<ProjectCommand> commands;

    private final Map<Class<? extends ProjectCommand>, ProjectCommand> commandMap = new HashMap<>();

    private final ProjectRepository projectRepository;

    @Transactional
    public Project setProjectStatusIsRegistered(long projectId) {
        Project project = getProjectWith(projectId);

        ProjectCommand command = getProjectCommand(RegisteredCommand.class);
        return command.execute(RegisteredCommand.createProjectContext(project));
    }

    @Transactional
    public Project setProjectStatusIsProceed(long projectId) {
        Project project = getProjectWith(projectId);

        ProjectCommand command = getProjectCommand(ProceedCommand.class);
        return command.execute(ProceedCommand.createProjectContext(project));
    }

    @Transactional
    public Project setProjectStatusIsReject(long projectId) {
        Project project = getProjectWith(projectId);

        ProjectCommand command = getProjectCommand(RejectCommand.class);
        return command.execute(RejectCommand.createProjectContext(project));
    }

    @Transactional
    public Project setProjectStatusIsDone(long projectId) {
        Project project = getProjectWith(projectId);

        ProjectCommand command = getProjectCommand(DoneCommand.class);
        return command.execute(DoneCommand.createProjectContext(project));
    }

    private Project getProjectWith(long projectId) {
        return projectRepository
                .findById(projectId)
                .orElseThrow(IllegalArgumentException::new);
    }

    private ProjectCommand getProjectCommand(Class<? extends ProjectCommand> clazz) {
        return commandMap.computeIfAbsent(clazz, this::findProjectCommand);
    }

    private <T> ProjectCommand findProjectCommand(Class<? extends ProjectCommand> clazz) {
        return commands.stream()
                .filter(command -> clazz.isAssignableFrom(command.getClass()))
                .findAny()
                .orElse(null);
    }
}
