package com.luv2code.springboot.cruddemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
@Aspect
public class DemoLoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.cruddemo.controller.*.*(..))")
    public void forControllerPackage(){}
    @Pointcut("execution(* com.luv2code.springboot.cruddemo.dao.*.*(..))")
    public void forDaoPackage(){}
    @Pointcut("execution(* com.luv2code.springboot.cruddemo.service.*.*(..))")
    public void forServicePackage(){}
    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    public void forControllerDaoOrServicePackage(){}

    @Before("forControllerDaoOrServicePackage()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        logger.info("In @Before advice calling method: "+method);
        Object[] args = joinPoint.getArgs();
//        for (Object arg: args) System.out.println("method arguments: "+arg);

    }

    @AfterReturning(pointcut = "forControllerDaoOrServicePackage()", returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        String method = joinPoint.getSignature().toShortString();
        logger.info("In @AfterReturning advice calling method: "+method);
        logger.info("The result: "+result);

    }


}
