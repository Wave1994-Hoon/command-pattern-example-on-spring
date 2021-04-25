package com.hoon.commandpattern.project.command;

import com.hoon.commandpattern.project.handler.ProceedHandler;
import com.hoon.commandpattern.project.handler.ProjectCommandHandler;
import com.hoon.commandpattern.project.handler.RegisteredHandler;
import com.hoon.commandpattern.project.model.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@AllArgsConstructor
public class RegisteredCommand implements ProjectCommand {

    private final List<ProjectCommandHandler> handlers;

    private final Map<Class<? extends ProjectCommandHandler>, ProjectCommandHandler> handlerMap = new HashMap<>();

    @PostConstruct
    private void configureHandler() {
        handlers.add(getHandler(RegisteredHandler.class));
    }

    public static ProjectContext createProjectContext(Project project) {
        return ProjectContext.createProjectContext(project);
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
    public Project execute(ProjectContext projectContext) {
        handlers.forEach(handler -> handler.handle(projectContext));
        return projectContext.getProject();
    }

}
