package com.hoon.commandpattern.project.command;

import com.hoon.commandpattern.project.handler.ProjectCommandHandler;
import java.util.List;

public interface ProjectCommand {

    ProjectContext execute(ProjectContext projectContext, List<ProjectCommandHandler> handlers);
}
