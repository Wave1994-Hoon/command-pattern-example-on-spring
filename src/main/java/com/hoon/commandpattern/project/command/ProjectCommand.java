package com.hoon.commandpattern.project.command;

import com.hoon.commandpattern.project.model.Project;


public interface ProjectCommand {

    Project execute(ProjectContext projectContext);
}
