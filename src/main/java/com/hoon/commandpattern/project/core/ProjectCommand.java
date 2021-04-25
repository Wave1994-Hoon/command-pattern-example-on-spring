package com.hoon.commandpattern.project.core;

import com.hoon.commandpattern.project.model.Project;


public interface ProjectCommand {

    Project execute(ProjectContext projectContext);
}
