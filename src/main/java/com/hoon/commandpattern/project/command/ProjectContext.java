package com.hoon.commandpattern.project.command;

import com.hoon.commandpattern.project.model.Project;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;


@Data
public class ProjectContext {
    private static final Logger logger = LoggerFactory.getLogger(ProjectContext.class);
//    private static final ThreadLocal<ProjectContext> threadLocal = new NamedThreadLocal<>(ProjectContext.class.getSimpleName());

//    @Getter(AccessLevel.PRIVATE)
//    private final Map<String, Object> map = new HashMap<>();

    private Project project;

//    private ProjectCommand projectCommand;

    private ProjectContext(Project project) {
        this.project = project;
    }

    public static ProjectContext createProjectContext(Project project) {
        ProjectContext projectContext = new ProjectContext(project);
        return projectContext;
    }

//    public static ProjectContext get() {
//        return threadLocal.get();
//    }
//
//    protected static void set(ProjectContext projectContext) {
//        threadLocal.set(projectContext);
//    }
//
//    protected static void unset() {
//        threadLocal.remove();
//    }
//
//    public Object getProperty(String key) {
//        return map.get(key);
//    }
//
//    @SuppressWarnings("unchecked")
//    public <T> T getProperty(String key, T def) {
//        T value = (T) map.get(key);
//        return value != null ? value : def;
//    }
//
//    public Object setProperty(String key, Object value) {
//        return map.put(key, value);
//    }
}
