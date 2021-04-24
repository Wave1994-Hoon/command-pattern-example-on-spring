package com.hoon.commandpattern.project.handler;

import com.hoon.commandpattern.project.command.ProjectContext;

public interface ProjectCommandHandler {

    boolean handle(ProjectContext projectContext);
}
