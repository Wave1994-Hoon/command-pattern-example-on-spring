package com.hoon.commandpattern.project.command;

import com.hoon.commandpattern.project.model.ProjectStatusType;
import lombok.Data;

@Data
public class ProjectContext {

    private ProjectStatusType projectStatus;
}
