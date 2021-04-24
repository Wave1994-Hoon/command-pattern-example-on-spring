package com.hoon.commandpattern.project.command;

public interface ProjectCommand {

    ProjectContext execute(ProjectContext projectContext);
}
