package com.hoon.commandpattern.project.command;

import com.hoon.commandpattern.project.ProjectRepository;
import com.hoon.commandpattern.project.handler.ProjectCommandHandler;
import com.hoon.commandpattern.project.model.Project;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Deprecated
public abstract class ProjectBaseCommand {
//
//    private final List<ProjectCommandHandler> handlers;
//    private final Map<Class<? extends ProjectCommandHandler>, ProjectCommandHandler> handlerMap = new HashMap<>();
//
//    private final ProjectRepository projectRepository;
//
//    @Override
//    public ProjectContext execute(ProjectContext projectContext) {
//        return null;
//    }
//
//    public static ProjectContext createProjectContext(long projectId) {
//        return ProjectContext.createProjectContext(projectId);
//    }
}
