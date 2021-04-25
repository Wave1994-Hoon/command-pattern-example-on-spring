package com.hoon.commandpattern.project.handler;

import com.hoon.commandpattern.project.core.ProjectContext;
import com.hoon.commandpattern.project.core.ProjectCommandHandler;
import com.hoon.commandpattern.project.model.Project;
import com.hoon.commandpattern.project.model.ProjectStatusType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@AllArgsConstructor
public class RejectHandler implements ProjectCommandHandler {

    @Override
    @Transactional
    public void handle(ProjectContext projectContext) {
        Project project = projectContext.getProject();
        project.setStatus(ProjectStatusType.REJECT); // Dirty Check By Hibernate
    }
}
