package com.hoon.commandpattern.project.core;

import com.google.common.util.concurrent.Striped;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Aspect
@Component
public class ProjectCommandExecutorAspect {

    private static final Striped<Lock> stripedLocks = Striped.lock(8); // set lock size

    @Around("@annotation(ProjectCommandExecutor)")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        /* [ProjectContext(map={}, project=com.hoon.commandpattern.project.model.Project@3c, projectCommand=null)] */
        ProjectContext projectContext = (ProjectContext)proceedingJoinPoint.getArgs()[0];
        ProjectContext.setThreadLocal(projectContext);

        long projectId = projectContext.getProject().getId();

        Object result;
        ReentrantLock lock = (ReentrantLock) stripedLocks.get(projectId);

        try {
            if (lock.tryLock()) {
                result = proceedingJoinPoint.proceed();
            } else {
                throw new IllegalAccessException();
            }
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

        return result;
    }

    @AfterReturning("@annotation(ProjectCommandExecutor)")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("AfterReturning");
        System.out.println(ProjectContext.getThreadLocal());
        ProjectContext.unsetThreadLocal();
    }

    @AfterThrowing("@annotation(ProjectCommandExecutor)")
    public void afterThrowing(JoinPoint joinPoint) {
        ProjectContext.unsetThreadLocal();
    }
}
