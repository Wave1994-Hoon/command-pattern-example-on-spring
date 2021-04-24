package com.hoon.commandpattern.project.command;

import com.hoon.commandpattern.project.handler.ProjectCommandHandler;
import java.util.List;

public abstract class ProjectBaseCommand implements ProjectCommand {

    @Override
    public ProjectContext execute(ProjectContext projectContext, List<ProjectCommandHandler> handlers) {

        handlers.forEach(ProjectCommandHandler::handle);
        return projectContext;
    }
}
