package com.milak.aspect;


import com.milak.model.User;
import com.milak.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {
    private static final Logger LOGGER = Logger.getLogger(LoggerAspect.class.getName());

    @Before("execution(* com.milak.service.UserService.findUserByUsername(..))")
    public void logBeforeFindingUser(JoinPoint joinPoint) {
        LOGGER.info("Finding user with method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.milak.service.UserService.findUserByUsername(..))")
    public void logAfterFindingUser(JoinPoint joinPoint) {
        LOGGER.info("User successfully found");
    }

    @After("execution(* com.milak.service.UserService.getAllUsers(..))")
    public void logAfterGettingUsers(JoinPoint joinPoint) {
        LOGGER.info("Requested all users with method: " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.milak.service.PostService.getAllPosts(..))")
    public void logBeforeGettingPosts(JoinPoint joinPoint) {
        LOGGER.info("Requested all posts with method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.milak.service.PostService.getAllPosts(..))")
    public void logAfterGettingPosts(JoinPoint joinPoint) {
        LOGGER.info("Successfully fetched all posts");
    }

    @AfterThrowing("within(com.milak.service.UserService)")
    public void logUserExceptions(JoinPoint joinPoint) {
        LOGGER.warning("Exception happened in UserService: " + joinPoint.toString());
    }

    @AfterThrowing("within(com.milak.service.PostService)")
    public void logPostExceptions(JoinPoint joinPoint) {
        LOGGER.warning("Exception happened in PostService: " + joinPoint.toString());
    }

    @AfterReturning(pointcut="execution(* com.milak.service.UserService.findUserByUsername(..))", returning = "user")
    public void findUserByUsernameReturn(User user) {
        LOGGER.info("Found user: " + user.getUsername());
    }
}
