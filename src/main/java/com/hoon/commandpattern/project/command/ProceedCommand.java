package com.hoon.commandpattern.project.command;

import com.hoon.commandpattern.project.ProjectRepository;
import com.hoon.commandpattern.project.handler.ProceedHandler;
import com.hoon.commandpattern.project.handler.ProjectCommandHandler;
import com.hoon.commandpattern.project.model.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ProceedCommand implements ProjectCommand {

    private static final List<ProjectCommandHandler> handlers = new ArrayList<>();
    private final Map<Class<? extends ProjectCommandHandler>, ProjectCommandHandler> handlerMap = new HashMap<>();

    private final ProjectRepository projectRepository;

    @PostConstruct
    private void configureHandler() {
        handlers.add(getHandler(ProceedHandler.class));
    }

    public static ProjectContext createProjectContext(long projectId) {
        return ProjectContext.createProjectContext(projectId);
    }

    private ProjectCommandHandler getHandler(Class<? extends ProjectCommandHandler> clazz) {
        return handlerMap.computeIfAbsent(clazz, this::findProjectHandler);
    }

    private ProjectCommandHandler findProjectHandler(Class<? extends ProjectCommandHandler> clazz) {
        return handlers.stream()
                .filter(k -> clazz.isAssignableFrom(k.getClass()) && clazz == k.getClass().getSuperclass())
                .findAny()
                .orElse(null);
    }

    @Transactional
    public ProjectContext preExecute(ProjectContext projectContext) {
        Project project =  projectRepository
                .findById(projectContext.getProjectId())
                .orElseThrow(IllegalArgumentException::new);

        projectContext.setProject(project);
        return projectContext;
    }

    public ProjectContext execute(ProjectContext projectContext) {
        handlers.forEach(handler -> handler.handle(projectContext));
        return projectContext;
    }
}
