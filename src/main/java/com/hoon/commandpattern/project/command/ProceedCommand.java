package com.hoon.commandpattern.project.command;

import com.hoon.commandpattern.project.ProjectRepository;
import com.hoon.commandpattern.project.handler.ProceedHandler;
import com.hoon.commandpattern.project.handler.ProjectCommandHandler;
import com.hoon.commandpattern.project.model.Project;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@AllArgsConstructor
public class ProceedCommand implements ProjectCommand {

    private final List<ProjectCommandHandler> handlers;

    private final Map<Class<? extends ProjectCommandHandler>, ProjectCommandHandler> handlerMap = new HashMap<>();

    @PostConstruct
    private void configureHandler() {
        handlers.add(getHandler(ProceedHandler.class));
    }

    public static ProjectContext createProjectContext(Project project) {
        ProjectContext projectContext = ProjectContext.createProjectContext(project);
        return projectContext;
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
