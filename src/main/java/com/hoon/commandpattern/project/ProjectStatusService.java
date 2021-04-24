package com.hoon.commandpattern.project;

import com.hoon.commandpattern.project.command.ProceedCommand;
import com.hoon.commandpattern.project.command.ProjectCommand;
import com.hoon.commandpattern.project.command.ProjectContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProjectStatusService {

    private final List<ProjectCommand> commands;
    private final Map<Class<? extends ProjectCommand>, ProjectCommand> commandMap = new HashMap<>();

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectContext setProjectStatusIsProceed(long projectId) {
        ProjectCommand command = getAdFlowCommand(ProceedCommand.class);
        return command.execute(ProceedCommand.createProjectContext(projectId));
    }

    private ProjectCommand getAdFlowCommand(Class<? extends ProjectCommand> clazz) {
        return commandMap.computeIfAbsent(clazz, this::findAdFlowCommand);
    }

    private <T> ProjectCommand findAdFlowCommand(Class<? extends ProjectCommand> clazz) {
        return commands.stream()
                .filter(k -> clazz.isAssignableFrom(k.getClass()))
                .findAny()
                .orElse(null);
    }

}