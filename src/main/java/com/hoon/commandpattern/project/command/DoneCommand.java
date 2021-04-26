package com.hoon.commandpattern.project.command;

import com.hoon.commandpattern.project.core.ProjectCommand;
import com.hoon.commandpattern.project.core.ProjectContext;
import com.hoon.commandpattern.project.core.ProjectCommandExecutor;
import com.hoon.commandpattern.project.handler.DoneHandler;
import com.hoon.commandpattern.project.core.ProjectCommandHandler;
import com.hoon.commandpattern.project.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class DoneCommand implements ProjectCommand {

    private final List<ProjectCommandHandler> handlers;

    private final Map<Class<? extends ProjectCommandHandler>, ProjectCommandHandler> handlerMap = new HashMap<>();

    @PostConstruct
    private void configureHandler() {
        handlers.add(getHandler(DoneHandler.class));
    }

    public static ProjectContext createProjectContext(Project project) {
        return ProjectContext.createProjectContext(project);
    }

    private ProjectCommandHandler getHandler(Class<? extends ProjectCommandHandler> clazz) {
        return handlerMap.computeIfAbsent(clazz, this::findProjectHandler);
    }

    private ProjectCommandHandler findProjectHandler(Class<? extends ProjectCommandHandler> clazz) {
        return handlers.stream()
                .filter(handler -> clazz.isAssignableFrom(handler.getClass()) && clazz == handler.getClass().getSuperclass())
                .findAny()
                .orElse(null);
    }

    @ProjectCommandExecutor
    @Transactional
    public Project execute(ProjectContext projectContext) {
        handlers.forEach(handler -> handler.handle(projectContext));
        return projectContext.getProject();
    }
}


