package com.hoon.commandpattern.project.core;

import com.hoon.commandpattern.project.model.Project;
import lombok.Data;
import org.springframework.core.NamedThreadLocal;


@Data
public class ProjectContext {

    private static final ThreadLocal<ProjectContext> threadLocal = new NamedThreadLocal<>(ProjectContext.class.getSimpleName());

    private Project project;

    private ProjectContext(Project project) {
        this.project = project;
    }

    public static ProjectContext createProjectContext(Project project) {
        return new ProjectContext(project);
    }

    public static ProjectContext getThreadLocal() {
        return threadLocal.get();
    }

    static void setThreadLocal(ProjectContext projectContext) {
        threadLocal.set(projectContext);
    }

    protected static void unsetThreadLocal() {
        threadLocal.remove();
    }
}
